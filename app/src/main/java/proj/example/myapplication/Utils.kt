package proj.example.myapplication

import android.support.design.widget.TextInputEditText
import proj.example.myapplication.Network.model.LoanApplication

/**
 * Created by stpl on 5/26/2017.
 */
fun TextInputEditText.isNotEmpty(): Boolean {
   return this.text.toString().trim().isNotEmpty()
}
fun TextInputEditText.getString():String{
    return this.text.toString()
}

fun LoanApplication.allParamatersSet():Boolean{
    return this.street.isNotEmpty()&&this.city.isNotEmpty()&&
            this.duration!=0&&this.pincode.isNotEmpty()&&
            this.loanAmount!=0&&this.state.isNotEmpty()
            &&this.purpose.isNotEmpty()
}