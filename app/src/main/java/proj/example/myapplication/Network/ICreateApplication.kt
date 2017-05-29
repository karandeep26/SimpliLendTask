package proj.example.myapplication.Network

import proj.example.myapplication.Network.model.LoanApplication
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by stpl on 5/29/2017.
 */
interface ICreateApplication {
    @POST("createApplication")
    fun createApplication(@Body requestBody:LoanApplication):Call<HashMap<String,Boolean>>
}