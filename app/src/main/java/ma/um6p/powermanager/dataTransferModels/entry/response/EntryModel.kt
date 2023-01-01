package ma.um6p.powermanager.dataTransferModels.entry.response

data class EntryModel(
    val __v: Int,
    val _id: String,
    val appInfo: AppInfo,
    val batteryInfo: BatteryInfo,
    val cpuInfo: CpuInfo,
    val createdAt: String,
    val memoryInfo: MemoryInfo,
    val summaryID: Int,
    val updatedAt: String
)