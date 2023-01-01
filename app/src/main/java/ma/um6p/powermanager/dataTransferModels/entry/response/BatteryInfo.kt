package ma.um6p.powermanager.dataTransferModels.entry.response

data class BatteryInfo(
    val _id: String,
    val batteryCapacity: Int,
    val batteryCurrent: Int,
    val batteryStatistics: Int,
    val batteryTemparature: Int,
    val batteryVoltage: Int,
    val createdAt: String,
    val energyConsumption: Int,
    val powerDemand: Int,
    val timeOfEntry: String,
    val updatedAt: String
)