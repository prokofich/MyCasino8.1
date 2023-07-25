package com.example.mycasino8.api

import com.example.mycasino8.model.ResponceWebView
import com.example.mycasino8.model.ResponseText
import com.example.mycasino8.model.RulesText
import retrofit2.Response

class Repository {

    suspend fun setParametersPhone(phone_name:String,locale:String,unique:String): Response<ResponceWebView> {
        return RetrofitInstance.api.setPostParametersPhone(phone_name, locale, unique)
    }

    suspend fun getTextFreeCash(): Response<ResponseText> {
        return RetrofitInstance.api.getTextFreeCash()
    }

    suspend fun getTextRulesGame(): Response<RulesText> {
        return RetrofitInstance.api.getTextRulesGame()
    }

}