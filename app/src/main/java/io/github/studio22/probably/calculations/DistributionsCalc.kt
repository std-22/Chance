package io.github.studio22.probably.calculations

import android.util.Log
import io.github.studio22.probably.extensions.round
import kotlin.math.pow


class DistributionsCalc {
    private val c = CombinatoricsCalc()

    fun binomialDistribution(eventQuantity: Int, eventProbability: Double): DoubleArray {
        val pArray = DoubleArray(eventQuantity + 1)
        for (i in 0..eventQuantity) {
            pArray[i] = (c.combinations(eventQuantity, i) *
                    eventProbability.pow(i) *
                    (1 - eventProbability).pow(eventQuantity - i)).round()

        }
        Log.d("LOOK_HERE", pArray[1].toString())
        return pArray
    }

    fun geomDistribution(eventProbability: Double): DoubleArray {
        val array = arrayListOf<Double>()
        var i = 0
        do {
            i += 1
            array.add((eventProbability * (1 - eventProbability).pow(i)).round())
        } while (array[i-1] != 0.0)
        return array.toDoubleArray()
    }

    fun poissonDistribution(lambda: Double): DoubleArray {
        val array = arrayListOf<Double>()
        var i = 0
        array.add((lambda.pow(i) / c.factorial(i) * Math.E.pow(-lambda)).round())
        do {
            i += 1
            array.add((lambda.pow(i) / c.factorial(i) * Math.E.pow(-lambda)).round())
        } while (array[i - 1] < array[i])
        i += 1
        array.add((lambda.pow(i) / c.factorial(i) * Math.E.pow(-lambda)).round())
        return array.toDoubleArray()
    }
}