package com.example.mycasino8.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino8.api.Repository
import com.example.mycasino8.model.RulesText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class RulesViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<RulesText>> = MutableLiveData()

    fun getTextRulesGame(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextRulesGame()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

}