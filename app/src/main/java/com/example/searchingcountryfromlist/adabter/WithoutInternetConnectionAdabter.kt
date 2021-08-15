package com.example.searchingcountryfromlist.adabter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchingcountryfromlist.R
import com.example.searchingcountryfromlist.model.WithoutInternetCountryModel
import kotlinx.android.synthetic.main.country_list.view.*



class WithoutInternetConnectionAdabter(val context: Context, val withoutinternetcountryLists: ArrayList<WithoutInternetCountryModel>):RecyclerView.Adapter<WithoutInternetConnectionAdabter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WithoutInternetConnectionAdabter.MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.withoutinternetcinnectionlist, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: WithoutInternetConnectionAdabter.MyViewHolder, position: Int) {
      holder.tvCapital.setText("Capital: " + withoutinternetcountryLists[position].capital)
      holder.tvRegion.setText("Region: " + withoutinternetcountryLists[position].region)
      holder.tvName.setText(withoutinternetcountryLists[position].name + " (" + withoutinternetcountryLists[position].alpha2Code + ")")



    }

    override fun getItemCount(): Int {
       return withoutinternetcountryLists.size-1
    }
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val flagImage = view.flagImage
        val tvName = view.tvName
        val tvCapital = view.tvCapital
        val tvRegion = view.tvRegion

    }


}




