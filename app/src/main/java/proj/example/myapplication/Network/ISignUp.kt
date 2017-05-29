package proj.example.myapplication.Network

import proj.example.myapplication.Network.model.SignUpRequest
import proj.example.myapplication.Network.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by stpl on 5/29/2017.
 */
interface ISignUp {
    @POST("register")
    fun signUp(@Body requestBody:SignUpRequest):Call<SignUpResponse>
}