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
        fun setGraphic(array: DoubleArray)
    }

    interface DistributionPresenter {
        fun setDistribution(
            distributionName: String,
            eventQuantity: Int = 0,
            eventProbability: Double = 0.0
        )
        fun getMathExp(): Double
        fun getDispersion(): Double
        fun getDistribution(): DoubleArray
//        fun setMathExpToModel(mathExp: Double)
//        fun setDispersionToModel(dispersion: Double)
//        fun setDistributionToModel(distribution: DoubleArray)
    }
}