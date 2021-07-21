package io.github.studio22.probably.distributions_module.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import io.github.studio22.probably.ContractInterface
import io.github.studio22.probably.R
import io.github.studio22.probably.distributions_module.DistributionActivity
import io.github.studio22.probably.distributions_module.presenter.DistributionPresenterImpl
import java.math.RoundingMode
import java.text.DecimalFormat


class SpecificDistributionActivity : ContractInterface.DistributionView,
    AppCompatActivity() {

    private var presenter: ContractInterface.DistributionPresenter? = null

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
        MaterialDialog(this).show {
            title(text = "Ввод данных")
            customView(R.layout.dialog)
            positiveButton {
                val eventNumber =
                    findViewById<EditText>(R.id.input_event_number).text.toString().toInt()
                val eventProbability =
                    findViewById<EditText>(R.id.input_event_probability).text.toString()
                        .toDouble()
                presenter?.setDistribution(distributionName, eventNumber, eventProbability)
                dismiss()
            }
            negativeButton {
                onSupportNavigateUp()
            }
        }
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
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        for (i in array.indices) {
            val eventNumberTV = findViewById<TextView>(eventNumbers[i])
            eventNumberTV.visibility = View.VISIBLE
            val eventProbabilityTV = findViewById<TextView>(eventNumberProbabilities[i])
            eventProbabilityTV.visibility = View.VISIBLE
            eventProbabilityTV.text = df.format(array[i])
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

    fun onClickBack(view: View) {
        val intent = Intent(this, DistributionActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
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
        super.onRestoreInstanceState(savedInstanceState)
    }
}