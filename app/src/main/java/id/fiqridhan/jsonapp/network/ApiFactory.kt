package id.fiqridhan.jsonapp.network

import id.fiqridhan.jsonapp.utils.Constant

object ApiFactory {

    val getData : ApiInterface = RetrofitFactory.retrofit(Constant.BASE_URL)
        .create(ApiInterface::class.java)

}