package proj.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.content_sign_up.*
import org.jetbrains.anko.startActivity
import proj.example.myapplication.Network.ISignUp
import proj.example.myapplication.Network.model.SignUpRequest
import proj.example.myapplication.Network.model.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@SuppressLint("ApplySharedPref")

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        toolbar.title="Sign Up"
        setSupportActionBar(toolbar)
        register.setOnClickListener({
            if(emailField.isNotEmpty()&& passwordField.isNotEmpty()
                    &&mobileContent.isNotEmpty()&&nameContent.isNotEmpty()){
                val signUp=SignUpRequest(nameContent.getString(),emailField.getString(),
                        passwordField.getString(),mobileContent.getString())
                ApplicationClass.retrofit.create(ISignUp::class.java).signUp(signUp)
                        .enqueue(object : Callback<SignUpResponse> {
                    override fun onResponse(call: Call<SignUpResponse>?, response: Response<SignUpResponse>) {
                        if (response.isSuccessful) {
                            val signUpResponse = response.body()
                            if (signUpResponse?.success as Boolean) {
                            getSharedPreferences("User Details", Context.MODE_PRIVATE).edit()
                                    .putInt("id",signUpResponse.id).commit()
                                startActivity<MainActivity>()
                            }
                        }
                    }
                    override fun onFailure(call: Call<SignUpResponse>?, t: Throwable?) {
                    }
                })

                }
            })
        }
    }

