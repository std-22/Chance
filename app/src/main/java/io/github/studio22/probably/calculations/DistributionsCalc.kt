package io.github.studio22.probably.calculations

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class DistributionsCalc {
    companion object

    fun binomialDistribution(n: Int, p: Double): DoubleArray {
        val c = CombinatoricsCalc()
        val pArray = DoubleArray(n + 1)
        for (i in 0..n) {
            pArray[i] = BigDecimal(c.combinations(n, i) * p.pow(i) * (1 - p).pow(n - i)).setScale(
                2,
                RoundingMode.HALF_EVEN
            ).toDouble()
        }
        return pArray
    }
}