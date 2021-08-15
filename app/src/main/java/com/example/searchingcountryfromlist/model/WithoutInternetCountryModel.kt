package com.example.searchingcountryfromlist.model

import android.widget.ImageView

class WithoutInternetCountryModel
{
        var id:Int?=null
        var name: String?=null
        var alpha2Code: String?=null
        var capital: String?=null
        var region: String?=null
        constructor(id:Int, name:String, alpha2code:String, capital:String, region:String){
            this.id=id
            this.name=name
            this.alpha2Code=alpha2code
            this.region=region
            this.capital=capital

        }
}
