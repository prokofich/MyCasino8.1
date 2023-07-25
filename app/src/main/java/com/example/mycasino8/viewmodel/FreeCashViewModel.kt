package com.example.mycasino8.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino8.api.Repository
import com.example.mycasino8.model.ResponseText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class FreeCashViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<ResponseText>> = MutableLiveData()

    fun getTextFreeCash(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextFreeCash()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

}