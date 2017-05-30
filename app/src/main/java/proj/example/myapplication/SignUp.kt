package proj.example.myapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
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
        toolbar.title = "Sign Up"
        var progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setCanceledOnTouchOutside(false)
        setSupportActionBar(toolbar)
        register.setOnClickListener({
            if (emailField.isNotEmpty() && passwordField.isNotEmpty()
                    && mobileContent.isNotEmpty() && nameContent.isNotEmpty()) {
                val signUp = SignUpRequest(nameContent.getString(), emailField.getString(),
                        passwordField.getString(), mobileContent.getString())
                progressDialog.show()
                ApplicationClass.retrofit.create(ISignUp::class.java).signUp(signUp)
                        .enqueue(object : Callback<SignUpResponse> {
                            override fun onResponse(call: Call<SignUpResponse>?, response: Response<SignUpResponse>) {
                                if (response.isSuccessful) {
                                    val signUpResponse = response.body()
                                    if (signUpResponse?.success as Boolean) {
                                        Toast.makeText(this@SignUp, "Registration Successful",
                                                Toast.LENGTH_SHORT).show()
                                        startActivity<MainActivity>()
                                    }
                                }
                                progressDialog.dismiss()
                            }

                            override fun onFailure(call: Call<SignUpResponse>?, t: Throwable) {
                                Toast.makeText(this@SignUp, t.localizedMessage, Toast.LENGTH_SHORT).show()
                                progressDialog.dismiss()
                            }
                        })

            } else {
                Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show()

            }
        })
    }
}

