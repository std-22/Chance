package io.github.studio22.probably.presenter

import android.util.Log
import io.github.studio22.probably.ContractInterface.DistributionView
import io.github.studio22.probably.ContractInterface.Presenter
import io.github.studio22.probably.model.DistributionModelImpl
import java.math.RoundingMode
import java.text.DecimalFormat

class DistributionPresenter(_view: DistributionView) : Presenter {
    private var view: DistributionView = _view
    private var model: DistributionModelImpl = DistributionModelImpl()

    override fun setDistribution(
        distributionName: String,
        eventQuantity: Int,
        eventProbability: Double
    ) {
        when (distributionName) {
            "Биноминальное" -> {
                model.binomialCalc(eventQuantity, eventProbability)
            }
        }
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING
        view.setDistributionProbability(model.distributionProbability)
        view.setMathExp(df.format(model.mathExp))
        view.setDispersion(df.format(model.dispersion))
    }
}