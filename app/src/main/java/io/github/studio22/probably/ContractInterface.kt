package io.github.studio22.probably

interface ContractInterface {

    interface Model {
        fun binomialCalc(eventNumber: Int, eventProbability: Double)
    }

    interface DistributionView {
        fun openDialog(distributionName: String)
        fun setDistributionProbability(array: DoubleArray)
        fun setMathExp(mathExp: String)
        fun setDispersion(dispersion: String)
    }

    interface Presenter {
        fun setDistribution(
            distributionName: String,
            eventQuantity: Int = 0,
            eventProbability: Double = 0.0
        )
    }
}