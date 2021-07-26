package io.github.studio22.probably.distributions_module.presenter

import io.github.studio22.probably.ContractInterface.DistributionPresenter
import io.github.studio22.probably.ContractInterface.DistributionView
import io.github.studio22.probably.distributions_module.model.DistributionModelImpl
import java.math.BigDecimal
import java.math.RoundingMode

class DistributionPresenterImpl(_view: DistributionView) : DistributionPresenter {
    private var view: DistributionView = _view
    var model: DistributionModelImpl = DistributionModelImpl()

    constructor(
        distributionView: DistributionView,
        distribution: DoubleArray?,
        mathExp: Double?,
        dispersion: Double?
    ) : this(distributionView) {
        mathExp?.let {
            model.mathExp = BigDecimal(mathExp).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        }
        dispersion?.let {
            model.dispersion = BigDecimal(dispersion).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        }
        distribution?.let {
            model.distributionProbability = distribution
        }
    }

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

        view.setDistributionProbability(model.distributionProbability)
        view.setMathExp(model.mathExp.toString())
        view.setDispersion(model.dispersion.toString())
        view.setGraphic(model.distributionProbability)
    }

    override fun getMathExp() = model.mathExp

    override fun getDispersion() = model.dispersion

    override fun getDistribution() = model.distributionProbability

    override fun toString(): String {
        return "${model.mathExp}, ${model.dispersion}"
    }
}