package io.github.studio22.probably

interface MVPContractInterface {

    interface DistributionModel {
        fun binomialModel(eventQuantity: Int, eventProbability: Double)
        fun geomModel(eventProbability: Double)
        fun poissonModel(lambda: Double)
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
        fun setBinomialDistribution(eventQuantity: Int, eventProbability: Double)
        fun setPoissonDistribution(lambda: Double)
        fun setExpDistribution(lambda: Double)
        fun setGeomDistribution(eventProbability: Double)
        fun setUniformDistribution(inputStart: Double, inputEnd: Double)
        fun getMathExp(): Double
        fun getDispersion(): Double
        fun getDistribution(): DoubleArray
    }
}