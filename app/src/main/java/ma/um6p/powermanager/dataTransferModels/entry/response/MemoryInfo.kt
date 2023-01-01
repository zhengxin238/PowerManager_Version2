package ma.um6p.powermanager.dataTransferModels.entry.response

data class MemoryInfo(
    val _id: String,
    val memoryStatistics: Double,
    val totalMemory: Int,
    val usedMemory: Int
)