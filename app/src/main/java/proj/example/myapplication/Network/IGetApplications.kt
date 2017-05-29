package proj.example.myapplication.Network

import proj.example.myapplication.Network.model.LoanApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by karan on 27/5/17.
 */
interface IGetApplications {
    @GET("getApplications")
    fun getList(@Header("id") id: Int): Call<MutableList<LoanApplication>>
}