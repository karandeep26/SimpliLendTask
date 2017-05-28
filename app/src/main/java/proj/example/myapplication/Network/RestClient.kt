package proj.example.myapplication.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by karan on 27/5/17.
 */
class RestClient {
    //    init {
    var retrofit = Retrofit.Builder().baseUrl("http://192.168.0.104:8080/demo/")
            .addConverterFactory(GsonConverterFactory.create()).build()


}


