package proj.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import proj.example.myapplication.Network.IGetApplications
import proj.example.myapplication.Network.model.LoanApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar.title="My Profile"
        setSupportActionBar(toolbar)
        apply.setOnClickListener { startActivity<CreateLoanApplicationActivity>() }
        view.setOnClickListener {
            ApplicationClass.retrofit.create(IGetApplications::class.java)
                    .getList(5).enqueue(object : Callback<MutableList<LoanApplication>> {
                override fun onFailure(call: Call<MutableList<LoanApplication>>?, t: Throwable?) {
                }

                override fun onResponse(call: Call<MutableList<LoanApplication>>?, response: Response<MutableList<LoanApplication>>?) {
                    if (response!!.isSuccessful) {
                        print(response.body().toString())
                        startActivity(intentFor<ViewLoanApplications>("list " to response.body()))
                    }
                }
            })
        }

    }

}
