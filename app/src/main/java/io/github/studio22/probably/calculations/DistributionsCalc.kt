package io.github.studio22.probably.calculations

import kotlin.math.pow

class DistributionsCalc {
    companion object fun binomialDistribution(n: Int, p: Double): DoubleArray {
        val c = CombinatoricsCalc()
        val pArray = DoubleArray(n + 1)
        for (i in 0..n) {
            pArray[i] = c.combinations(n, i) *
                    p.pow(i) * (1 - p).pow(n - i)
        }
        return pArray
    }
}