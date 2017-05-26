package proj.example.myapplication

import android.support.design.widget.TextInputEditText

/**
 * Created by stpl on 5/26/2017.
 */
fun TextInputEditText.isNotEmpty(): Boolean {
   return this.text.toString().isNotEmpty()
}
