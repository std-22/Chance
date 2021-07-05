package io.github.studio22.probably.model

import io.github.studio22.probably.ContractInterface
import io.github.studio22.probably.calculations.DistributionsCalc

class DistributionModelImpl: ContractInterface.Model {
    private val d = DistributionsCalc()
    lateinit var distributionProbability: DoubleArray
    var mathExp: Double = 0.0
    var dispersion: Double = 0.0

    override fun binomialCalc(eventNumber: Int, eventProbability: Double) {
        distributionProbability = d.binomialDistribution(eventNumber, eventProbability)
        mathExp = eventNumber * eventProbability
        dispersion = eventNumber * eventProbability * (1 - eventProbability)
    }
}
