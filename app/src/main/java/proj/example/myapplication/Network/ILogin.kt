package proj.example.myapplication.Network

import proj.example.myapplication.Network.model.LoginRequest
import proj.example.myapplication.Network.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by stpl on 5/29/2017.
 */
interface ILogin {
    @POST("login")
    fun login(@Body requestBody:LoginRequest):Call<LoginResponse>
}