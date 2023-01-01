package ma.um6p.powermanager.dataTransferModels.entry.source

data class MemoryInfoOriginal(
    val memoryStatistics: Double,
    val totalMemory: Int,
    val usedMemory: Int
)