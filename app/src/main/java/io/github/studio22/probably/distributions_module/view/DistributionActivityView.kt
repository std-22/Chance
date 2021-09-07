package io.github.studio22.probably.distributions_module.view

import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import io.github.studio22.probably.MVPContractInterface
import io.github.studio22.probably.R
import io.github.studio22.probably.distributions_module.DistributionDialogFragment
import io.github.studio22.probably.distributions_module.presenter.DistributionPresenterImpl
import io.github.studio22.probably.extensions.setStyle


class DistributionActivityView : MVPContractInterface.DistributionView,
    AppCompatActivity(), DistributionDialogFragment.DialogListener {
    private var presenter: MVPContractInterface.DistributionPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_distribution)

        setActionBar()

        if (savedInstanceState == null) {
            presenter = DistributionPresenterImpl(this)
            openDialog(intent.extras?.get("distribution_name").toString())
        }
    }

    private fun setActionBar() {
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.title = intent.extras?.get("distribution_name").toString()
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun openDialog(distributionName: String) {
        val dialog = DistributionDialogFragment(intent.extras?.get("distribution_name").toString())
        dialog.show(supportFragmentManager, "dialog")
    }

    override fun setDistributionProbability(array: DoubleArray) {
//        val eventNumbersTextView = findViewById<TextView>(R.id.event_numbers)
//        eventNumbersTextView.visibility = View.VISIBLE
//        val eventProbabilitiesTextView = findViewById<TextView>(R.id.event_probabilities)
//        eventProbabilitiesTextView.visibility = View.VISIBLE
        val params1: TableRow.LayoutParams =
            TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1.0f
            )
        val params2: TableRow.LayoutParams =
            TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
        val tableLayout = findViewById<TableLayout>(R.id.table)
        val eventNumbersTableRow = TableRow(this)
        for (i in array.indices) {
            val textView = TextView(this)
            textView.text = i.toString()
            textView.layoutParams = params1
            textView.setStyle()
            eventNumbersTableRow.addView(textView)
        }
        eventNumbersTableRow.layoutParams = params2
        tableLayout.addView(eventNumbersTableRow)
        val eventProbabilityTableRow = TableRow(this)
        for (i in array.indices) {
            val textView = TextView(this)
            textView.text = array[i].toString()
            textView.layoutParams = params1
            textView.setStyle()
            eventProbabilityTableRow.addView(textView)
        }
        eventProbabilityTableRow.layoutParams = params2
        tableLayout.addView(eventProbabilityTableRow)

    }

    override fun setMathExp(mathExp: String) {
        val textView = findViewById<TextView>(R.id.math_expect)
        textView.visibility = View.VISIBLE
        val mathExpectationTV = findViewById<TextView>(R.id.math_expect_value)
        mathExpectationTV.text = mathExp
    }

    override fun setDispersion(dispersion: String) {
        val textView = findViewById<TextView>(R.id.dispersion)
        textView.visibility = View.VISIBLE
        val dispersionTV = findViewById<TextView>(R.id.dispersion_value)
        dispersionTV.text = dispersion
    }

    override fun setGraphic(array: DoubleArray) {
        val graphicName = findViewById<TextView>(R.id.graphic_name)
        graphicName.visibility = View.VISIBLE
        val mChart = findViewById<BarChart>(R.id.distribution_graphic)
        mChart.visibility = View.VISIBLE

        val entries: ArrayList<BarEntry> = ArrayList()
        var prevSum = 0.0
        for (i in array.indices) {
            prevSum += array[i]
            entries.add(BarEntry(i.toFloat(), prevSum.toFloat()))
        }

        val dataSet = BarDataSet(entries, "Label")
        val barData = BarData(dataSet)
        mChart.data = barData
        mChart.invalidate()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter?.let { outState.putDouble("MATH_EXP", it.getMathExp()) }
        presenter?.let { outState.putDouble("DISPERSION", it.getDispersion()) }
        presenter?.let { outState.putDoubleArray("DISTRIBUTION", it.getDistribution()) }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        presenter =
            DistributionPresenterImpl(
                this,
                savedInstanceState.getDoubleArray("DISTRIBUTION"),
                savedInstanceState.getDouble("MATH_EXP"),
                savedInstanceState.getDouble("DISPERSION")
            )
        setMathExp(presenter?.getMathExp().toString())
        setDispersion(presenter?.getDispersion().toString())
        presenter?.getDistribution()?.let { setDistributionProbability(it) }
        presenter?.getDistribution()?.let { setGraphic(it) }
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        onSupportNavigateUp()
    }

    override fun onDialogPositiveClick(
        eventQuantity: Int,
        eventProbability: Double,
        lambda: Double
    ) {
        when (intent.extras?.get("distribution_name").toString()) {
            resources.getString(R.string.binomial) ->
                presenter?.setBinomialDistribution(eventQuantity, eventProbability)
            resources.getString(R.string.poisson) ->
                presenter?.setPoissonDistribution(lambda)
            resources.getString(R.string.geometric) ->
                presenter?.setGeomDistribution(eventProbability)
        }
    }

}