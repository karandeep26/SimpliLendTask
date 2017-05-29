package proj.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity
import proj.example.myapplication.Network.ILogin
import proj.example.myapplication.Network.model.LoginRequest
import proj.example.myapplication.Network.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title="SimpliLend"
        setSupportActionBar(toolbar)
        signUp.setOnClickListener({
            startActivity<SignUp>()
        })

        login.setOnClickListener({
            if (loginPassword.isNotEmpty() && emailContent.isNotEmpty()) {
                val loginRequest = LoginRequest(emailContent.getString(), loginPassword.getString())
                ApplicationClass.retrofit.create(ILogin::class.java).login(loginRequest)
                        .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse?.success as Boolean) {
                                startActivity<HomeActivity>()
                            }
                        }
                    }
                })
            }
        })

    }
}
