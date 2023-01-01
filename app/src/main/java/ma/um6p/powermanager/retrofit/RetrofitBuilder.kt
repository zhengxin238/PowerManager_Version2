package ma.um6p.powermanager.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://192.168.178.80:3002/"

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //Create a OkHttpClient
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    // Create a Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //create a retrofit instance
    private  val retrofit = builder.build()


    // *What will be the dataBuildType,  as a parameter in the following function we will pass  a class which implements the interface that we have just defined as dataservice,

    fun <T> buildData(dataBuildType: Class<T>): T{
        return retrofit.create(dataBuildType)
    }
}