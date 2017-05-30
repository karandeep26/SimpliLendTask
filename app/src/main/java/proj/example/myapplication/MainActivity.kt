package proj.example.myapplication

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
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
        var progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setCanceledOnTouchOutside(false)
        var sharedPref=getSharedPreferences("user",Context.MODE_PRIVATE)
        if(sharedPref.getBoolean("isLogin",false)){
            startActivity<HomeActivity>()
        }
        signUp.setOnClickListener({
            startActivity<SignUp>()
        })

        login.setOnClickListener({
            if (loginPassword.isNotEmpty() && emailContent.isNotEmpty()) {
                val loginRequest = LoginRequest(emailContent.getString(), loginPassword.getString())
                progressDialog.show()
                ApplicationClass.retrofit.create(ILogin::class.java).login(loginRequest)
                        .enqueue(object : Callback<LoginResponse> {

                    override fun onFailure(call: Call<LoginResponse>?, t: Throwable) {
                        Toast.makeText(this@MainActivity,t.localizedMessage,Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }

                    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse?.success as Boolean) {

                                var editor=sharedPref.edit()
                                editor.putBoolean("isLogin",true)
                                editor.putInt("id",loginResponse.id)
                                editor.commit()
                                startActivity<HomeActivity>()
                                finish()
                            }
                            else{
                                Toast.makeText(this@MainActivity,"Invalid Credentials",Toast.LENGTH_SHORT).show()
                            }
                        }

                        progressDialog.dismiss()
                    }
                })
            }
            else{
                Toast.makeText(this@MainActivity,"Fill All Fields",Toast.LENGTH_SHORT).show()

            }
        })

    }
}
