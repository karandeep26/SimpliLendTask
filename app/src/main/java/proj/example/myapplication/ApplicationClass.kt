package proj.example.myapplication

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by karan on 27/5/17.
 */
class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    companion object {
        var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).baseUrl("http://192.168.0.104:8080/demo/")
                .build()
    }

}