package com.silverstudio.cowintracker

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CowinDetailsAdapter(private var exampleList: List<Cowin>) :
        RecyclerView.Adapter<CowinDetailsAdapter.ExampleViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View?, obj: Cowin?, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = mItemClickListener
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cowin,
                parent, false)
        return ExampleViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.cowinId.text = currentItem.centerId.toString();
        holder.centerName.text = currentItem.centerName.toString();
        holder.city.text = currentItem.cityname.toString();

        if (currentItem.mininumage.toString()=="18")
            holder.age.text = "18-45"
        else
            holder.age.text = "45+"


        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val date = formatter.parse(currentItem.date)
        var month = SimpleDateFormat("MMM").format(date)

        val temp = currentItem.date.substring(0, 2)

        val day : Int? = temp.toIntOrNull()

        holder.date1.text = month +  " ${day?.plus(1)} "
        holder.date2.text = month +" ${day?.plus(1)} "
        holder.date3.text = month +" ${day?.plus(2)} $month"
        holder.date4.text = month + " ${day?.plus(3)} $month"
        holder.date5.text = month + " ${day?.plus(4)} $month"
        holder.date6.text = month + "${day?.plus(5)} $month"


    }




        override fun getItemCount() = exampleList.size


    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cowinId: TextView = itemView.findViewById(R.id.cowinid)
        val centerName: TextView = itemView.findViewById(R.id.centername)
        val city: TextView = itemView.findViewById(R.id.city)
        val age: TextView = itemView.findViewById(R.id.age)
        val date1: TextView = itemView.findViewById(R.id.textview1)
        val date2: TextView = itemView.findViewById(R.id.textview2)
        val date3: TextView = itemView.findViewById(R.id.textview3)
        val date4: TextView = itemView.findViewById(R.id.textview4)
        val date5: TextView = itemView.findViewById(R.id.textview5)
        val date6: TextView = itemView.findViewById(R.id.textview6)




    }

    fun setData(list222: List<Cowin>) {
        exampleList = list222 as ArrayList<Cowin>

        notifyDataSetChanged()
    }



    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}
