package proj.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_view_loan_applications.*
import proj.example.myapplication.Network.model.LoanApplication

class ViewLoanApplications : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_loan_applications)
        var list=intent.getSerializableExtra("list") as MutableList<LoanApplication>
        var adapter = LoanListAdapter()
        adapter.setList(list)
        recyclerView.adapter=LoanListAdapter()
        recyclerView.layoutManager= LinearLayoutManager(this)
    }
}
