package ma.um6p.powermanager.dataTransferModels.entry.response

data class CpuInfo(
    val _id: String,
    val cpuCores: Int,
    val cpuFrequencies: Int,
    val cpuStatistics: Int
)