package com.example.searchingcountryfromlist

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchingcountryfromlist.model.CountryModel
import com.example.searchingcountryfromlist.model.WithoutInternetCountryModel
import com.example.searchingcountryfromlist.retrofit.ApiService
import com.example.searchingcountryfromlist.retrofit.RetroInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CountryViewModel: ViewModel() {

     var liveDataList: MutableLiveData<List<CountryModel>>

    init {
        liveDataList = MutableLiveData()
    }


    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>> {
        return liveDataList
    }

    fun makeAPICall(query:String) {
        val retroInstance = RetroInstance.getRetroInstance()?.create(ApiService::class.java)
        retroInstance?.getCountryList(query+"%")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(getCountryListObserver());
    }

    private fun getCountryListObserver(): io.reactivex.Observer<List<CountryModel>> {
        return object :io.reactivex.Observer<List<CountryModel>>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: List<CountryModel>) {

                liveDataList.postValue(t)
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

        }
    }

}