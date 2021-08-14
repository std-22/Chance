package io.github.studio22.probably.distributions_module.model

import io.github.studio22.probably.MVPContractInterface.DistributionModel
import io.github.studio22.probably.calculations.DistributionsCalc
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class DistributionModelImpl : DistributionModel {
    private val d = DistributionsCalc()
    var distributionProbability: DoubleArray = DoubleArray(10)
    var mathExp: Double = 0.0
    var dispersion: Double = 0.0

    override fun binomialModel(eventQuantity: Int, eventProbability: Double) {
        distributionProbability = d.binomialDistribution(eventQuantity, eventProbability)
        mathExp = BigDecimal(eventQuantity * eventProbability)
            .setScale(2, RoundingMode.HALF_EVEN).toDouble()
        dispersion = BigDecimal(eventQuantity * eventProbability * (1 - eventProbability))
            .setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    override fun geomShortModel(eventQuantity: Int, eventProbability: Double) {
        distributionProbability = d.geomShortDistribution(eventQuantity, eventProbability)
        mathExp = BigDecimal(1 / eventProbability)
            .setScale(2, RoundingMode.HALF_EVEN).toDouble()
        dispersion = BigDecimal(eventProbability / (1 - eventProbability).pow(2))
            .setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    override fun geomModel(eventProbability: Double) {
    }

    override fun poissonModel(eventQuantity: Int, lambda: Double) {
        distributionProbability = d.poissonDistribution(eventQuantity, lambda)
        mathExp = BigDecimal(lambda).setScale(2, RoundingMode.HALF_EVEN).toDouble()
        dispersion = BigDecimal(lambda).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    override fun expModel(lambda: Double) {
    }

    override fun uniformModel(start: Double, end: Double) {
    }


}
