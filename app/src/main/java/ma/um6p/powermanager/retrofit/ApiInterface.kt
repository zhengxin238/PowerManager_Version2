package ma.um6p.powermanager.retrofit


import ma.um6p.powermanager.dataTransferModels.dynamicInfo.DynamicInfoOriginal
import ma.um6p.powermanager.dataTransferModels.entry.response.EntryResponse
import ma.um6p.powermanager.dataTransferModels.entry.source.DateModel
import ma.um6p.powermanager.dataTransferModels.entry.source.StoreFullEntryModel
import ma.um6p.powermanager.dataTransferModels.entry.source.SummaryID
import ma.um6p.powermanager.dataTransferModels.summary.SummaryResponse
import ma.um6p.powermanager.dataTransferModels.summary.QuerySummaryDataWithAppTitleAndStartTimeModel
import ma.um6p.powermanager.dataTransferModels.summary.UpdateFullSummaryWithIDModel
import ma.um6p.powermanager.dataTransferModels.summary.StoreFullSummaryModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {
////Summary
    @GET("api/entry/summarylist")
    fun getSummaryData(): Call<SummaryResponse>
    @POST("api/entry/storeFullSummary")
    fun addSummary(@Body newSummary: StoreFullSummaryModel): Call<SummaryResponse>
    @POST("/api/entry/updateFullSummaryWithID")
    fun updateFullSummaryWithID(@Body newUpdateSummary: UpdateFullSummaryWithIDModel) : Call<SummaryResponse>
    @POST("api/entry/querySummaryDataWithAppTItleAndStartTime")
    fun querySummaryDataWithAppTItleAndStartTime(@Body newQuery: QuerySummaryDataWithAppTitleAndStartTimeModel) : Call<SummaryResponse>





////Entry
    @GET("api/entry/entrylist")
    fun getEntryData(): Call<EntryResponse>
    @POST("api/entry/storeFullEntry")
    fun addEntry(@Body newEntry: StoreFullEntryModel): Call<String>
    @POST("api/entry/showEntryBySummaryId")
    fun showEntryBySummaryId(@Body newSummaryID: SummaryID): Call<EntryResponse>
    @POST("api/entry/showEntryBetweenDate")
    fun  showEntryBetweenDate(@Body newDateModel:DateModel): Call<EntryResponse>


////DynamicInfo
    @POST("api/entry/storeDynamicInfo")
    fun storeDynamicInfo(@Body newDynamicInfoOriginal: DynamicInfoOriginal): Call<String>



}