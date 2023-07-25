package com.example.mycasino8.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.mycasino8.R
import com.example.mycasino8.constant.*
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_rules.*
import kotlinx.android.synthetic.main.fragment_settings.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        //загрузка фоновой картинки
        loadBackground()

        //загрузка картинки монеты
        loadBackgroundImage(url_image_freecash_moneta,id_menu_iv_img_cash)

        //показ количества денег
        id_menu_tv_cash.text = MAIN.getMyCash().toString()

        //обработка нажатия на кнопку ИГРАТЬ
        id_menu_button_play.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_difficultGameFragment)
        }

        //обработка нажатия на кнопку ПРАВИЛА
        id_menu_button_tutorials.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_rulesFragment)
        }

        //обработка нажатия на кнопку НАСТРОЙКИ
        id_menu_button_settings.setOnClickListener {
            MAIN.navController.navigate(R.id.action_menuFragment_to_settingsFragment)
        }

        //обработка нажатия на кнопку ВЫЙТИ
        id_menu_button_down.setOnClickListener {
            MAIN.finishAffinity()
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.finishAffinity()
        }


    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция загрузки фоновой картинки
    private fun loadBackground(){
        when(MAIN.getMyNumberBackground()){
            1 -> { loadBackgroundImage(url_image_background1,id_menu_img) }
            2 -> { loadBackgroundImage(url_image_background2,id_menu_img) }
            3 -> { loadBackgroundImage(url_image_background3,id_menu_img) }
        }
    }

    //функция замены цвета текста в вьюхах
    @SuppressLint("ResourceAsColor")
    private fun changeViewColorText(){
        when(MAIN.getMyColorText()){
            BLACK_TEXT -> {
                val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
                id_menu_tv_title_dice.setTextColor(blackColor)
                id_menu_tv_cash.setTextColor(blackColor)
                id_menu_button_play.setTextColor(blackColor)
                id_menu_button_tutorials.setTextColor(blackColor)
                id_menu_button_settings.setTextColor(blackColor)
                id_menu_button_down.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_menu_tv_title_dice.setTextColor(whiteColor)
                id_menu_tv_cash.setTextColor(whiteColor)
                id_menu_button_play.setTextColor(whiteColor)
                id_menu_button_tutorials.setTextColor(whiteColor)
                id_menu_button_settings.setTextColor(whiteColor)
                id_menu_button_down.setTextColor(whiteColor)
            }
        }
    }

}