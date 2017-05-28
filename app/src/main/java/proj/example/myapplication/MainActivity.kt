package proj.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.startActivity
import proj.example.myapplication.Network.IGetApplications
import proj.example.myapplication.Network.model.LoanApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signUp.setOnClickListener({
            startActivity<SignUp>()
        })
        login.setOnClickListener({

        })
        ApplicationClass.retrofit.create(IGetApplications::class.java).getList(5).enqueue(object : Callback<ArrayList<LoanApplication>> {
            override fun onFailure(call: Call<ArrayList<LoanApplication>>?, t: Throwable?) {
                Log.d("error", t?.localizedMessage.toString())
            }

            override fun onResponse(call: Call<ArrayList<LoanApplication>>?, response: Response<ArrayList<LoanApplication>>?) {
                if (response!!.isSuccessful) {
                    print(response.body().toString())
                }
            }
        })

    }
}
