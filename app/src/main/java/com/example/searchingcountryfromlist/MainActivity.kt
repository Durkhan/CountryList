package com.example.searchingcountryfromlist

import android.app.Service
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchingcountryfromlist.adabter.CountryListAdabter
import com.example.searchingcountryfromlist.adabter.WithoutInternetConnectionAdabter
import com.example.searchingcountryfromlist.model.CountryModel
import com.example.searchingcountryfromlist.model.WithoutInternetCountryModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.country_list.*
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {
    lateinit var recyclerAdapter: CountryListAdabter
    lateinit var withoutInternetConnectionAdabter: WithoutInternetConnectionAdabter
    var withoutinternetcountryList=ArrayList<WithoutInternetCountryModel>()
    var connectivity:ConnectivityManager?=null
    var context=this
    var info:NetworkInfo?=null
    var internetconnection:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
   


        if (checkinternetconnection()==true){
        countryListRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdabter(this)
        countryListRecyclerview.adapter =recyclerAdapter
            countyr.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    ViewModel(s.toString() + "%")
                   
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
        }

        else if (checkinternetconnection()==false){
            countryListRecyclerview.layoutManager = LinearLayoutManager(this)
            withoutInternetConnectionAdabter= WithoutInternetConnectionAdabter(this,withoutinternetcountryList)
            countryListRecyclerview.adapter =withoutInternetConnectionAdabter
            withoutInternetConnectionAdabter.notifyDataSetChanged()
            var database=Database(this)
            var cursor=database.Query()
            if (cursor.moveToFirst()){
                do {
                    var ctrname=cursor.getString(cursor.getColumnIndex("NAME"))
                    var ctrcapital=cursor.getString(cursor.getColumnIndex("CAPITAL"))
                    var ctralphacod=cursor.getString(cursor.getColumnIndex("ALPHACOD"))
                    var ctrregion=cursor.getString(cursor.getColumnIndex("REGION"))
                    var ctrid=cursor.getInt(cursor.getColumnIndex("ID"))

                    withoutinternetcountryList.add(WithoutInternetCountryModel(ctrid,ctrname,ctralphacod,ctrcapital,ctrregion))
                }while (cursor.moveToNext())
            }

        }


    }





    fun checkinternetconnection():Boolean{
        connectivity=context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity!=null){
            info=connectivity!!.activeNetworkInfo
            if (info!=null){
                if (info!!.state==NetworkInfo.State.CONNECTED) {
                    internetconnection = true
                }
            }
            else
                internetconnection=false
        }
      return internetconnection
  }




    private fun ViewModel(query:String){
        val viewModel:CountryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, object: Observer<List<CountryModel>> {
            override fun onChanged(countrymodellist: List<CountryModel>?) {
                if(countrymodellist!= null) {
                    var database=Database(context)
                    var values= ContentValues()

                    for (i in 1..countrymodellist.size-1) {
                        values.put("NAME", countrymodellist[i].name)
                        values.put("ID",i)
                        values.put("CAPITAL",countrymodellist[i].capital)
                        values.put("REGION", countrymodellist[i].alpha2Code)
                        values.put("ALPHACOD",countrymodellist[i].alpha2Code)
                        database.Insert(values)


                    }
                    recyclerAdapter.setCountryList(countrymodellist)
                    recyclerAdapter.notifyDataSetChanged()
                }
            }

        })
        viewModel.makeAPICall(query)
    }

    override fun onResume() {
        super.onResume()
        checkinternetconnection()
    }
}


