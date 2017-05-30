package proj.example.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.loan_item.view.*
import proj.example.myapplication.Network.model.LoanApplication


/**
 * Created by stpl on 5/29/2017.
 */
class LoanListAdapter :RecyclerView.Adapter<LoanListAdapter.ViewHolder>(){
    var dataList: MutableList<LoanApplication>?=null
    fun setList(dataList:MutableList<LoanApplication>){
        this.dataList=dataList
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(dataList!![position])
    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.loan_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(dataList!=null){
            return (dataList as MutableList<LoanApplication>).size
        }
        return 0
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindView(item: LoanApplication){
            with(item){
                itemView.amount_content.text=item.loanAmount.toString()
                itemView.street_content.text=item.street+","
                itemView.city_content.text=item.city+","
                itemView.state_content.text=item.state+","
                itemView.pincode_content.text=item.pincode
                itemView.tenure_content.text=item.duration.toString()+" Months"
            }
        }
    }

}