package proj.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import proj.example.myapplication.Network.IGetApplications
import proj.example.myapplication.Network.model.LoanApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        ApplicationClass.retrofit.create(IGetApplications::class.java).getList(5).enqueue(object : Callback<ArrayList<LoanApplication>> {
            override fun onFailure(call: Call<ArrayList<LoanApplication>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ArrayList<LoanApplication>>?, response: Response<ArrayList<LoanApplication>>?) {
                if (response!!.isSuccessful) {
                    print(response.body().toString())
                }
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

    }

}
