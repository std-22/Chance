package io.github.studio22.probably.model

import io.github.studio22.probably.ContractInterface.Model
import io.github.studio22.probably.calculations.DistributionsCalc

class DistributionModelImpl: Model {
    private val d = DistributionsCalc()
    var distributionProbability: DoubleArray = DoubleArray(10)
    var mathExp: Double = 0.0
    var dispersion: Double = 0.0

    override fun binomialCalc(eventNumber: Int, eventProbability: Double) {
        distributionProbability = d.binomialDistribution(eventNumber, eventProbability)
        mathExp = eventNumber * eventProbability
        dispersion = eventNumber * eventProbability * (1 - eventProbability)
    }
}
