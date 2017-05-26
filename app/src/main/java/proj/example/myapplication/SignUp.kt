package proj.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_sign_up.*

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUp.setOnClickListener({
            if(emailField.isNotEmpty()&& passwordField.isNotEmpty()&&userIdContent.isNotEmpty()
                    &&mobileContent.isNotEmpty()&&nameContent.isNotEmpty()){
                
            }
        })
    }
}
