package ma.um6p.powermanager.dataTransferModels.summary

data class StoreFullSummaryModel(

    var appTitle: String,
    var averagePower: Double,
  //  val endTime: String,
    var energyConsumption: Double,
    var executionTime: Double
  //  val startTime: String

)