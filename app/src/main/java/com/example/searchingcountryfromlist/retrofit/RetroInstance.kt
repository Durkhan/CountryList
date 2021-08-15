package com.example.searchingcountryfromlist.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        private var retrofit:Retrofit?=null
        private var baseurl="https://restcountries.eu/rest/"

        fun getRetroInstance(): Retrofit? {
            if (retrofit==null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit
        }
    }


}