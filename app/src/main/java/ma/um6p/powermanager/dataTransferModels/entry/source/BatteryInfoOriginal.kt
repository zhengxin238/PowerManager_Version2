package ma.um6p.powermanager.dataTransferModels.entry.source

data class BatteryInfoOriginal(

    val batteryCapacity: Int,
    val batteryCurrent: Int,
    val batteryStatistics: Int,
    val batteryTemparature: Int,
    val batteryVoltage: Int,

    val energyConsumption: Int,
    val powerDemand: Int,
    val timeOfEntry: String,

)