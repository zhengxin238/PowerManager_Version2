package ma.um6p.powermanager.dataTransferModels.summary

data class StoreFullSummaryModel(

    val appTitle: String,
    val averagePower: Double,
    val endTime: String,
    val energyConsumption: Double,
    val startTime: String
)