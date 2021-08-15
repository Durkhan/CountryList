package com.example.searchingcountryfromlist.retrofit

import com.example.searchingcountryfromlist.model.CountryModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2?")
    fun getCountryList(@Query("q=") query:String): Observable<List<CountryModel>>
}