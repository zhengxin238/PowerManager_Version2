package ma.um6p.powermanager.dataTransferModels.entry.response

import com.google.gson.annotations.SerializedName
import ma.um6p.powermanager.dataTransferModels.entry.response.EntryModel

data class EntryResponse(
    @SerializedName("response" )
    val responseTwo: List<EntryModel>
)