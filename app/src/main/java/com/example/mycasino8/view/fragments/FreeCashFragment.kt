package com.example.mycasino8.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino8.R
import com.example.mycasino8.constant.*
import com.example.mycasino8.viewmodel.FreeCashViewModel
import kotlinx.android.synthetic.main.fragment_free_cash.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.*
import org.threeten.bp.LocalDate

class FreeCashFragment : Fragment() {

    private var job: Job = Job()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_free_cash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //проверка "прошли ли сутки после последнего приза"
        if(LocalDate.now().toString() == MAIN.getLastDay()){
            MAIN.navController.navigate(R.id.action_freeCashFragment_to_menuFragment)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        val freeCashViewModel = ViewModelProvider(this)[FreeCashViewModel::class.java]
        freeCashViewModel.getTextFreeCash()
        freeCashViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_freecash_tv_title.text = TEXT.body()!!.text
        }

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background_freecash,id_freecash_img)

        //загрузка картинки монеты
        loadBackgroundImage(url_image_freecash_moneta,id_freecash_iv_img_cash)

        //обработка нажатия кнопки получения денежного приза
        id_freecash_button_get_money.setOnClickListener {
            it.isEnabled = false
            job = CoroutineScope(Dispatchers.Main).launch {
                id_freecash_tv_title.isVisible = false
                id_freecash_cs_win_cash.isVisible = true
                id_freecash_tv_cash.text = listWinCash.shuffled()[0].toString()
                MAIN.addCash(id_freecash_tv_cash.text.toString().toInt())
                MAIN.setCurrentDay(LocalDate.now().toString())
                showToast("${id_freecash_tv_cash.text.toString().toInt()}$")
                delay(2500)
                MAIN.navController.navigate(R.id.action_freeCashFragment_to_menuFragment)
            }


        }


    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    //функция замены цвета текста в вьюхах
    @SuppressLint("ResourceAsColor")
    private fun changeViewColorText(){
        when(MAIN.getMyColorText()){
            BLACK_TEXT -> {
                val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
                id_freecash_tv_title.setTextColor(blackColor)
                id_freecash_button_get_money.setTextColor(blackColor)
                id_freecash_tv_cash.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_freecash_tv_title.setTextColor(whiteColor)
                id_freecash_button_get_money.setTextColor(whiteColor)
                id_freecash_tv_cash.setTextColor(whiteColor)
            }
        }
    }


}