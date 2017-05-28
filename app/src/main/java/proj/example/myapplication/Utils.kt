package proj.example.myapplication

import android.support.design.widget.TextInputEditText
import retrofit2.Retrofit

/**
 * Created by stpl on 5/26/2017.
 */
fun TextInputEditText.isNotEmpty(): Boolean {
   return this.text.toString().isNotEmpty()
}

fun Retrofit.getService(serviceClass: Any): Any {
    return this.create(serviceClass::class.java)
}

