package io.github.studio22.probably.distributions_module.model

import io.github.studio22.probably.ContractInterface.Model
import io.github.studio22.probably.calculations.DistributionsCalc
import java.math.BigDecimal
import java.math.RoundingMode

class DistributionModelImpl : Model {
    private val d = DistributionsCalc()
    var distributionProbability: DoubleArray = DoubleArray(10)
    var mathExp: Double = 0.0
    var dispersion: Double = 0.0

    override fun binomialCalc(eventNumber: Int, eventProbability: Double) {
        distributionProbability = d.binomialDistribution(eventNumber, eventProbability)
        mathExp = BigDecimal(eventNumber * eventProbability).setScale(2, RoundingMode.HALF_EVEN)
            .toDouble()
        dispersion = BigDecimal(eventNumber * eventProbability * (1 - eventProbability)).setScale(
            2,
            RoundingMode.HALF_EVEN
        ).toDouble()
    }
}
