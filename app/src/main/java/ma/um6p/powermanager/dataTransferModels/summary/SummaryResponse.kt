package ma.um6p.powermanager.dataTransferModels.summary

import ma.um6p.powermanager.dataTransferModels.summary.SummaryResponseItem
import com.google.gson.annotations.SerializedName

data class SummaryResponse(
    @SerializedName("response" )
    val responseOne: List<SummaryResponseItem>


)