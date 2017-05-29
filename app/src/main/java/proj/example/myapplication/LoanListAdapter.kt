package proj.example.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import proj.example.myapplication.Network.model.LoanApplication

/**
 * Created by stpl on 5/29/2017.
 */
class LoanListAdapter :RecyclerView.Adapter<LoanListAdapter.ViewHolder>(){
    var dataList: MutableList<LoanApplication>? = null
    fun setList(dataList:MutableList<LoanApplication>){
        this.dataList=dataList
    }
    override fun onBindViewHolder(p0: ViewHolder?, p1: Int) {

    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.loan_item, p0, false)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        if(dataList!=null){
            return (dataList as MutableList<LoanApplication>).size
        }
        return 0
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {


    }

}