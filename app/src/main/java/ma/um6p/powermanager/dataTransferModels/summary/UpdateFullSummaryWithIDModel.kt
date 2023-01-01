package ma.um6p.powermanager.dataTransferModels.summary



data class UpdateFullSummaryWithIDModel (

    val summaryID: String,
    val appTitle: String,
    val startTime: String,
    val endTime: String,
    val averagePower: Int,
    val energyConsumption: Int

)