package io.github.studio22.probably.calculations

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class DistributionsCalc {
    private val c = CombinatoricsCalc()

    fun binomialDistribution(eventQuantity: Int, eventProbability: Double): DoubleArray {
        val pArray = DoubleArray(eventQuantity + 1)
        for (i in 0..eventQuantity) {
            pArray[i] = BigDecimal(c.combinations(eventQuantity, i) * eventProbability.pow(i) * (1 - eventProbability).pow(eventQuantity - i)).setScale(
                2,
                RoundingMode.HALF_EVEN
            ).toDouble()
        }
        return pArray
    }

    fun geomShortDistribution(eventQuantity: Int, eventProbability: Double): DoubleArray {
        val array = DoubleArray(eventQuantity + 1)
        for (i in 0..eventQuantity) {
            array[i] = eventProbability * (1 - eventProbability).pow(i)
        }
        return array
    }

    fun geomDistribution() {

    }

    fun poissonDistribution(eventQuantity: Int, lambda: Double): DoubleArray {
        val array = DoubleArray(eventQuantity + 1)
        for (i in 0..eventQuantity) {
            array[i] = lambda.pow(i) / c.factorial(i) * Math.E.pow(-lambda)
        }
        return array
    }
}