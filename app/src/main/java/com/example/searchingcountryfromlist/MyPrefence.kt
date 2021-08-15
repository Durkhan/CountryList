package com.example.searchingcountryfromlist

import android.content.Context

class MyPrefence(context: Context) {
    private var SHARD_PREFENCE="Shared"
    private var SHARED_INT="Int"
    val prefence=context.getSharedPreferences(SHARD_PREFENCE,Context.MODE_PRIVATE)

     fun getsharedcountriest(key:String):String{
        return prefence.getString(key,"").toString()
    }
    fun setsharedcountriest(key:String,st:String){
        val editor=prefence.edit()
        editor.putString(key,st)
        editor.apply()
    }
    fun getsharedcountries(): Int{
        return prefence.getString(SHARED_INT,"")!!.toInt()
    }
    fun setsharedcountries(t:Int){
        val editor=prefence.edit()
        editor.putInt(SHARED_INT,t)
        editor.apply()
    }
}