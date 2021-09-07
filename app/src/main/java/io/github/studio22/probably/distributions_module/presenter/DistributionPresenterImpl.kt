package io.github.studio22.probably.distributions_module.presenter

import io.github.studio22.probably.MVPContractInterface.DistributionPresenter
import io.github.studio22.probably.MVPContractInterface.DistributionView
import io.github.studio22.probably.distributions_module.model.DistributionModelImpl
import java.math.BigDecimal
import java.math.RoundingMode

class DistributionPresenterImpl(_view: DistributionView) : DistributionPresenter {
    private var view: DistributionView = _view
    private var model: DistributionModelImpl = DistributionModelImpl()

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

    override fun setBinomialDistribution(eventQuantity: Int, eventProbability: Double) {
        model.binomialModel(eventQuantity, eventProbability)
        setView()
    }

    override fun setPoissonDistribution(lambda: Double) {
        model.poissonModel(lambda)
        setView()
    }

    override fun setExpDistribution(lambda: Double) {
        TODO("Not yet implemented")
    }

    override fun setGeomDistribution(eventProbability: Double) {
        model.geomModel(eventProbability)
        setView()
    }

    override fun setUniformDistribution(inputStart: Double, inputEnd: Double) {
        model.uniformModel(inputStart, inputEnd)
    }


    private fun setView() {
        view.setDistributionProbability(model.distributionProbability)
        view.setMathExp(model.mathExp.toString())
        view.setDispersion(model.dispersion.toString())
        view.setGraphic(model.distributionProbability)
    }

    override fun getMathExp() = model.mathExp

    override fun getDispersion() = model.dispersion

    override fun getDistribution() = model.distributionProbability
}