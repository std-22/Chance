package io.github.studio22.probably

interface MVPContractInterface {

    interface DistributionModel {
        fun binomialModel(eventQuantity: Int, eventProbability: Double)
        fun geomShortModel(eventQuantity: Int, eventProbability: Double)
        fun geomModel(eventProbability: Double)
        fun poissonModel(eventQuantity: Int, lambda: Double)
        fun expModel(lambda: Double)
        fun uniformModel(start: Double, end: Double)

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
            eventProbability: Double = 0.0,
            lambda: Double = 0.0
        )
        fun getMathExp(): Double
        fun getDispersion(): Double
        fun getDistribution(): DoubleArray
    }
}