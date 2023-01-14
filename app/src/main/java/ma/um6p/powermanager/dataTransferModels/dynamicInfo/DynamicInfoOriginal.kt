package ma.um6p.powermanager.dataTransferModels.dynamicInfo

data class DynamicInfoOriginal(
    var appName: String,
    var launchDate: String,
    var executionTime:String,
    var batteryStatistics: Double,
    var batteryCapacity: Int,
    var batteryTemparature: Int,
    var batteryVoltage: Double,
    var batteryCurrent:Int,
    var powerDemand: Int,
    var energyConsumption: Double,
    var cpuStatistics: Int,
    var cpuCores: Int,
    var cpuFrequencies: Array<Int>,
    var memoryStatistics: String,
    var totalMemory: String,
    var usedMemory: String
)
