package ma.um6p.powermanager.dataTransferModels.entry.source

import ma.um6p.powermanager.dataTransferModels.entry.source.AppInfoOriginal
import ma.um6p.powermanager.dataTransferModels.entry.source.BatteryInfoOriginal
import ma.um6p.powermanager.dataTransferModels.entry.source.CpuInfoOriginal
import ma.um6p.powermanager.dataTransferModels.entry.source.MemoryInfoOriginal

data class StoreFullEntryModel(

    val appInfo: AppInfoOriginal,
    val batteryInfo: BatteryInfoOriginal,
    val cpuInfo: CpuInfoOriginal,
    val memoryInfo: MemoryInfoOriginal,
    val summaryID: Int,

    )
