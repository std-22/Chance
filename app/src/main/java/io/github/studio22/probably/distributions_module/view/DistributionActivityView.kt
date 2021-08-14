package io.github.studio22.probably.distributions_module.view

import android.os.Bundle
import android.view.View
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
        val dialog = DistributionDialogFragment()
        dialog.show(supportFragmentManager, "")
    }

    override fun setDistributionProbability(array: DoubleArray) {
        val eventNumbers = intArrayOf(
            R.id.event_0,
            R.id.event_1,
            R.id.event_2,
            R.id.event_3,
            R.id.event_4,
            R.id.event_5,
            R.id.event_6,
            R.id.event_7
        )
        val eventNumberProbabilities = intArrayOf(
            R.id.p0,
            R.id.p1,
            R.id.p2,
            R.id.p3,
            R.id.p4,
            R.id.p5,
            R.id.p6,
            R.id.p7
        )
        for (i in array.indices) {
            val eventNumberTV = findViewById<TextView>(eventNumbers[i])
            eventNumberTV.visibility = View.VISIBLE
            val eventProbabilityTV = findViewById<TextView>(eventNumberProbabilities[i])
            eventProbabilityTV.visibility = View.VISIBLE
            eventProbabilityTV.text = array[i].toString()
        }
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

    override fun onDialogPositiveClick(eventQuantity: Int, eventProbability: Double) {
        presenter?.setDistribution(
            intent.extras?.get("distribution_name").toString(),
            eventQuantity,
            eventProbability
        )
    }
}