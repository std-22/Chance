package io.github.studio22.probably.distributions_module.model

import io.github.studio22.probably.MVPContractInterface.DistributionModel
import io.github.studio22.probably.calculations.DistributionsCalc
import io.github.studio22.probably.extensions.round
import kotlin.math.pow

class DistributionModelImpl : DistributionModel {
    private val d = DistributionsCalc()
    var distributionProbability: DoubleArray = DoubleArray(10)
    var mathExp: Double = 0.0
    var dispersion: Double = 0.0

    override fun binomialModel(eventQuantity: Int, eventProbability: Double) {
        distributionProbability = d.binomialDistribution(eventQuantity, eventProbability)
        mathExp = (eventQuantity * eventProbability).round()
        dispersion = (eventQuantity * eventProbability * (1 - eventProbability)).round()

    }

    override fun geomModel(eventProbability: Double) {
        distributionProbability = d.geomDistribution(eventProbability)
        mathExp = (1 / eventProbability).round()
        dispersion = eventProbability / (1 - eventProbability).pow(2).round()
    }

    override fun poissonModel(lambda: Double) {
        distributionProbability = d.poissonDistribution(lambda)
        mathExp = lambda.round()
        dispersion = lambda.round()
    }

    override fun expModel(lambda: Double) {
        TODO("Not yet implemented")
    }

    override fun uniformModel(start: Double, end: Double) {
        TODO("Not yet implemented")
    }
}
