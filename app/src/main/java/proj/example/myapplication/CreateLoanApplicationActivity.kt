package proj.example.myapplication

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_loan_application.*
import kotlinx.android.synthetic.main.content_loan_application.*
import org.jetbrains.anko.startActivity
import proj.example.myapplication.Network.ICreateApplication
import proj.example.myapplication.Network.model.LoanApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateLoanApplicationActivity : AppCompatActivity() {
    var loanApplication = LoanApplication()
    val purposeList = ArrayList<String>()
    val stateList = ArrayList<String>()
    val cityList= ArrayList<String>()
    var monthList=Array(12,{i->(i+1).toString()})
    var purposeAdapter:ArrayAdapter<String>?=null
    var cityAdapter:ArrayAdapter<String>?=null
    var monthAdapter:ArrayAdapter<String>?=null
    var stateAdapter:ArrayAdapter<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_application)
        toolbar.title = "Create Loan Application"
        setSupportActionBar(toolbar)
        var progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setCanceledOnTouchOutside(false)
        setData()
        setAdapters()
        setItemSelectListeners()
        loanApplication.userId=getSharedPreferences("user",Context.MODE_PRIVATE).getInt("id",-1)
        submit_application.setOnClickListener {
            if(loanApplication.userId!=-1) {
                loanApplication.street = addressContent.text.toString()
                loanApplication.pincode = pincode.text.toString()
                loanApplication.loanAmount = amountContent.text.toString().toInt()
                if (loanApplication.allParamatersSet()) {
                    progressDialog.show()
                    ApplicationClass.retrofit.create(ICreateApplication::class.java)
                            .createApplication(loanApplication)
                            .enqueue(object : Callback<HashMap<String, Boolean>> {
                                override fun onResponse(call: Call<HashMap<String, Boolean>>,
                                                        response: Response<HashMap<String, Boolean>>) {
                                    if (response.isSuccessful) {
                                        val resp = response.body()
                                        if (resp != null && resp["success"] as Boolean) {
                                            Toast.makeText(this@CreateLoanApplicationActivity,
                                                    "Application Created", Toast.LENGTH_SHORT).show()
                                            startActivity<HomeActivity>()
                                        }
                                    }
                                    progressDialog.dismiss()
                                }

                                override fun onFailure(call: Call<HashMap<String, Boolean>>?, t: Throwable) {
                                    Toast.makeText(this@CreateLoanApplicationActivity,t.toString(),Toast.LENGTH_SHORT).show()
                                    progressDialog.dismiss()
                                }
                            })
                } else {
                    Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    fun itemSelectListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                var currentPosition=position-1
                if(currentPosition>=0) {
                    when (parent.id) {
                        stateSpinner.id -> {
                            loanApplication.state = stateList[currentPosition]
                        }
                        citySpinner.id -> {
                            loanApplication.city = cityList[currentPosition]
                        }
                        purposeSpinner.id -> {
                            loanApplication.purpose = purposeList[currentPosition]
                        }
                        monthsSpinner.id -> {
                            loanApplication.duration = monthList[currentPosition].toInt()
                        }

                    }
                }
            }
        }
    }
    fun setData(){
        purposeList.add("Car")
        purposeList.add("Home")
        purposeList.add("Personal")
        purposeList.add("Education")
        stateList.add("Maharashtra")
        stateList.add("Karnataka")
        stateList.add("Haryana")
        cityList.add("Mumbai")
        cityList.add("Bangalore")
        cityList.add("Gurgaon")
    }
    fun setAdapters(){
        purposeAdapter=ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, purposeList)
        cityAdapter=ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cityList)
        monthAdapter=ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, monthList)
        stateAdapter=ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stateList)
        purposeSpinner.adapter = NothingSelectedSpinnerAdapter(purposeAdapter,R.layout.nothing_selected_purpose,this)
        citySpinner.adapter=NothingSelectedSpinnerAdapter(cityAdapter,R.layout.nothing_selected_city,this)
        stateSpinner.adapter=NothingSelectedSpinnerAdapter(stateAdapter,R.layout.nothing_selected_state,this)
        monthsSpinner.adapter=NothingSelectedSpinnerAdapter(monthAdapter,R.layout.nothing_selected_month,this)
    }
    fun setItemSelectListeners(){
        purposeSpinner.onItemSelectedListener = itemSelectListener()
        citySpinner.onItemSelectedListener =itemSelectListener()
        stateSpinner.onItemSelectedListener = itemSelectListener()
        monthsSpinner.onItemSelectedListener=itemSelectListener()
    }

}


