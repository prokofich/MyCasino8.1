package com.example.mycasino8.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mycasino8.R
import com.example.mycasino8.constant.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MAIN = this
        navController = Navigation.findNavController(this,R.id.id_nav_host)

        //устновка полноэкранного режима
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    //функция установки номера аватарки
    fun setMyNumberSmile(number:Int){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_NUMBER_IMAGE_SMILE,number)
            .apply()
    }

    //функция получения номера аватарки
    fun getMyNumberSmile():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(MY_NUMBER_IMAGE_SMILE, 1)
    }

    //функция установки номера фоновой картинки
    fun setMyNumberBackground(number:Int){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_NUMBER_IMAGE_BACKGROUND,number)
            .apply()
    }

    //функция получения номера фоновой картинки
    fun getMyNumberBackground():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(MY_NUMBER_IMAGE_BACKGROUND, 1)
    }

    //функция установки цвета текста
    fun setMyColorText(color:String){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putString(MY_COLOR_TEXT,color)
            .apply()
    }

    //функция получения цвета текста
    fun getMyColorText():String{
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getString(MY_COLOR_TEXT,"")
        return preferences ?: ""
    }

    //функция установки пола
    fun setMySex(sex:String){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putString(MY_SEX,sex)
            .apply()
    }

    //функция установки имени
    fun setMyName(name:String){
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putString(MY_NAME,name)
            .apply()
    }

    //функция получения последнего дня,когда юзеб брал приз
    fun getLastDay():String{
        val preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getString(LAST_DAY,"")
        return preferences ?: ""
    }

    //функция установки последнего дня,когда юзер брал приз
    fun setCurrentDay(day:String){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        preferences.edit()
            .putString(LAST_DAY,day)
            .apply()
    }

    //функция получения своих денег
    fun getMyCash(): Int {
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(MY_CASH, 0)
    }

    //функция добавления денег
    fun addCash(cash:Int){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getInt(MY_CASH,0) + cash
        val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_CASH,preferences)
            .apply()
    }

    //функция покупки чего либо
    fun minusCash(cash: Int){
        val preferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE).getInt(MY_CASH,0) - cash
        val pref = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE)
        pref.edit()
            .putInt(MY_CASH,preferences)
            .apply()
    }

    //функция получения имени
    fun getMyName(): String {
        val preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getString(MY_NAME,"")
        return preferences ?: ""
    }

    //функция получения пола
    fun getMySex(): String {
        val preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getString(MY_SEX,"")
        return preferences ?: ""
    }

}