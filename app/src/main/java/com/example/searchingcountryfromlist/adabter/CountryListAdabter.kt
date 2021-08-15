package com.example.searchingcountryfromlist.adabter

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchingcountryfromlist.Database
import com.example.searchingcountryfromlist.R
import com.example.searchingcountryfromlist.model.CountryModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.country_list.view.*

class CountryListAdabter(val context: Context): RecyclerView.Adapter<CountryListAdabter.MyViewHolder>() {
    private var countryList: List<CountryModel>? = null



    fun setCountryList(countryList: List<CountryModel>?) {
        this.countryList = countryList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdabter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdabter.MyViewHolder, position: Int) {

        holder.tvCapital.setText("Capital: "+countryList?.get(position)?.capital)
        holder.tvName.setText(countryList?.get(position)?.name +"(" + countryList?.get(position)?.alpha2Code+")")
        holder.tvRegion.setText("Region"+countryList?.get(position)?.region)
        GlideToVectorYou.justLoadImage(context as Activity?,
            Uri.parse(countryList?.get(position)?.flag),holder.flagImage)



    }

    override fun getItemCount(): Int {
        if(countryList == null) return 0;
        else return countryList?.size!!
    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        val flagImage = view.flagImage
        val tvName = view.tvName
        val tvCapital = view.tvCapital
        val tvRegion = view.tvRegion

    }
}