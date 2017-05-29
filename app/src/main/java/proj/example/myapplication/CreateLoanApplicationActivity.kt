package proj.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_loan_application.*
import kotlinx.android.synthetic.main.content_loan_application.*
import proj.example.myapplication.Network.ICreateApplication
import proj.example.myapplication.Network.model.LoanApplication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateLoanApplicationActivity : AppCompatActivity() {
    var loanApplication = LoanApplication()
    val purposeList = mutableListOf("CAR,HOME,EDUCATION,PERSONAL")
    val stateList = mutableListOf("Maharashtra,Karnataka,Haryana")
    val cityList= mutableListOf("Mumbai,Bangalore,Gurgaon")
    val yearList= Array(5,{i->(i*i).toString()})
    var monthList=Array(12,{i->(i*i).toString()})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_application)
        toolbar.title = "Create Loan Application"
        setSupportActionBar(toolbar)
        purposeSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, purposeList)
        citySpinner.adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cityList)
        stateSpinner.adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,stateList)
//        yearSpinner.adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,yearList)
        monthsSpinner.adapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,monthList)
        purposeSpinner.onItemSelectedListener = itemSelectListener()
        citySpinner.onItemSelectedListener =itemSelectListener()
        stateSpinner.onItemSelectedListener = itemSelectListener()
        monthsSpinner.onItemSelectedListener=itemSelectListener()
        submit_application.setOnClickListener {
            loanApplication.address=addressContent.toString()
            loanApplication.pincode=pincode.toString()
            loanApplication.loanAmount=amountContent.text.toString().toInt()
            if(loanApplication.address.isNotEmpty()&&loanApplication.city.isNotEmpty()&&
                    loanApplication.duration!=0&&loanApplication.pincode.isNotEmpty()&&
                    loanApplication.loanAmount!=0&&loanApplication.state.isNotEmpty()
                    &&loanApplication.purpose.isNotEmpty()){
                ApplicationClass.retrofit.create(ICreateApplication::class.java).createApplication(loanApplication)
                        .enqueue(object : Callback<HashMap<String, Boolean>> {
                            override fun onResponse(call: Call<HashMap<String, Boolean>>, response: Response<HashMap<String, Boolean>>) {
                                if(response.isSuccessful){
                                   var resp=response.body()
                                   Log.d("RESPONSE:",resp.toString())
                                }
                            }

                            override fun onFailure(call: Call<HashMap<String, Boolean>>?, t: Throwable?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }
                        })

            }
            else{
                Toast.makeText(this,"Fill all fields",Toast.LENGTH_SHORT).show()
            }
        }
//        yearSpinner.onItemSelectedListener=itemSelectListener()


    }

    fun itemSelectListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (parent.id) {
                    stateSpinner.id->{
                        loanApplication.state=stateList[position]
                    }
                    citySpinner.id->{
                        loanApplication.city=cityList[position]
                    }
                    purposeSpinner.id->{
                        loanApplication.purpose=purposeList[position]
                    }
                    monthsSpinner.id-> {
                        loanApplication.duration = monthList[position].toInt()
                    }

                }
            }
        }
    }

}


