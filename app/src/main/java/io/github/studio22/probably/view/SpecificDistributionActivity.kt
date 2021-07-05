package io.github.studio22.probably.view

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import io.github.studio22.probably.ContractInterface
import io.github.studio22.probably.DistributionActivity
import io.github.studio22.probably.R
import io.github.studio22.probably.presenter.DistributionPresenter
import java.math.RoundingMode
import java.text.DecimalFormat


class SpecificDistributionActivity : AppCompatActivity(), ContractInterface.DistributionView {

    private var presenter: ContractInterface.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_specific_distribution)
        val textView = findViewById<TextView>(R.id.header_name)
        textView.text = intent.extras?.get("distribution_name").toString()

        presenter = DistributionPresenter(this)

        val distributions = resources.getStringArray(R.array.distributions)



        when (intent.extras?.get("distribution_name").toString()) {
            distributions[0] -> {
                openDialog("Биноминальное")
            }
        }

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
                Toast.makeText(context, "NEGATIVE", Toast.LENGTH_SHORT).show()
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
        val mathExpectationTV = findViewById<TextView>(R.id.math_expect_value)
        mathExpectationTV.text = mathExp
    }

    override fun setDispersion(dispersion: String) {
        val dispersionTV = findViewById<TextView>(R.id.dispersion_value)
        dispersionTV.text = dispersion
    }

}