package proj.example.myapplication

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
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
        var progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setCanceledOnTouchOutside(false)
        apply.setOnClickListener { startActivity<CreateLoanApplicationActivity>() }
        var sharedPref=getSharedPreferences("user",Context.MODE_PRIVATE)
        var id=sharedPref.getInt("id",-1)
        logOut.setOnClickListener {
            sharedPref.edit().clear().commit()
            startActivity<MainActivity>()
            finish()
        }
        view.setOnClickListener {
            progressDialog.show()
            ApplicationClass.retrofit.create(IGetApplications::class.java)
                    .getList(id).enqueue(object : Callback<ArrayList<LoanApplication>> {
                override fun onFailure(call: Call<ArrayList<LoanApplication>>?, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(this@HomeActivity,t.localizedMessage,Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ArrayList<LoanApplication>>, response: Response<ArrayList<LoanApplication>>) {
                    if (response.isSuccessful) {
                        if(response.body()!!.size>0) {
                            var intent: Intent = Intent(this@HomeActivity, ViewLoanApplicationsActivity::class.java)
                            intent.putParcelableArrayListExtra("list", response.body())
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this@HomeActivity,"You Have No Applications",Toast.LENGTH_SHORT).show()
                        }
                    }
                    progressDialog.dismiss()
                }
            })
        }

    }

}
