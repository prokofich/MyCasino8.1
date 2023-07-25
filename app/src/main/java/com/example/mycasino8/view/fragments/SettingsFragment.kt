package com.example.mycasino8.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.mycasino8.R
import com.example.mycasino8.constant.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        //показ текущей аватарки
        showCurrentImageSmile()

        //загрузка фоновой картинки
        loadBackground()

        //загрузка смайликов
        loadImagesSmile()

        //загрузка имени
        id_set_et_name.hint = MAIN.getMyName()

        //установка 1 аватарки
        id_set_radiobutton1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                MAIN.setMyNumberSmile(1)
                id_set_radiobutton2.isChecked = false
                id_set_radiobutton3.isChecked = false
                id_set_radiobutton4.isChecked = false
            }
        }

        //установка 2 аватарки
        id_set_radiobutton2.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                MAIN.setMyNumberSmile(2)
                id_set_radiobutton1.isChecked = false
                id_set_radiobutton3.isChecked = false
                id_set_radiobutton4.isChecked = false
            }
        }

        //установка 3 аватарки
        id_set_radiobutton3.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                MAIN.setMyNumberSmile(3)
                id_set_radiobutton1.isChecked = false
                id_set_radiobutton2.isChecked = false
                id_set_radiobutton4.isChecked = false
            }
        }

        //установка 4 аватарки
        id_set_radiobutton4.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                MAIN.setMyNumberSmile(4)
                id_set_radiobutton1.isChecked = false
                id_set_radiobutton2.isChecked = false
                id_set_radiobutton3.isChecked = false
            }
        }

        //смена фоновой картинки
        id_set_button_change_background.setOnClickListener {
            when(MAIN.getMyNumberBackground()){
                1 -> {
                    MAIN.setMyNumberBackground(2)
                    loadBackgroundImage(url_image_background2,id_set_img)
                }
                2 -> {
                    MAIN.setMyNumberBackground(3)
                    loadBackgroundImage(url_image_background3,id_set_img)
                }
                3 -> {
                    MAIN.setMyNumberBackground(1)
                    loadBackgroundImage(url_image_background1,id_set_img)
                }
            }
        }

        id_set_switch.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                MAIN.setMyColorText(BLACK_TEXT)
            }else{
                MAIN.setMyColorText(WHITE_TEXT)
            }
            changeViewColorText()
        }

        //обработка выхода в меню+сохранение нового имени,если нужно
        id_set_button_menu.setOnClickListener {
            if(id_set_et_name.text.toString()!=""){
                MAIN.setMyName(id_set_et_name.text.toString())
            }
            MAIN.navController.navigate(R.id.action_settingsFragment_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(id_set_et_name.text.toString()!=""){
                MAIN.setMyName(id_set_et_name.text.toString())
            }
            MAIN.navController.navigate(R.id.action_settingsFragment_to_menuFragment)
        }



    }

    //функция загрузки фоновой картинки
    private fun loadBackground(){
        when(MAIN.getMyNumberBackground()){
            1 -> { loadBackgroundImage(url_image_background1,id_set_img) }
            2 -> { loadBackgroundImage(url_image_background2,id_set_img) }
            3 -> { loadBackgroundImage(url_image_background3,id_set_img) }
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

    //функция загрузки всех возможных аватарок
    private fun loadImagesSmile(){
        var list = emptyList<String>()
        when(MAIN.getMySex()){
            MALE -> {
                list = listUrlImagesSmileMale
            }
            FEMALE -> {
                list = listUrlImagesSmileFemale
            }
        }
        loadBackgroundImage(list[0],id_set_iv_img1)
        loadBackgroundImage(list[1],id_set_iv_img2)
        loadBackgroundImage(list[2],id_set_iv_img3)
        loadBackgroundImage(list[3],id_set_iv_img4)
    }

    //функция показа текущей аватарки
    private fun showCurrentImageSmile(){
        when(MAIN.getMyNumberSmile()){
            1 -> { id_set_radiobutton1.isChecked = true }
            2 -> { id_set_radiobutton2.isChecked = true }
            3 -> { id_set_radiobutton3.isChecked = true }
            4 -> { id_set_radiobutton4.isChecked = true }
        }
    }

    //функция замены цвета текста в вьюхах
    @SuppressLint("ResourceAsColor")
    private fun changeViewColorText(){
        when(MAIN.getMyColorText()){
            BLACK_TEXT -> {
                id_set_switch.isChecked = true
                val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
                id_set_button_change_background.setTextColor(blackColor)
                id_set_et_name.setTextColor(blackColor)
                id_set_tv_black_text.setTextColor(blackColor)
                id_set_radiobutton1.setTextColor(blackColor)
                id_set_radiobutton2.setTextColor(blackColor)
                id_set_radiobutton3.setTextColor(blackColor)
                id_set_radiobutton4.setTextColor(blackColor)
                id_set_button_menu.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                id_set_switch.isChecked = false
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_set_button_change_background.setTextColor(whiteColor)
                id_set_et_name.setTextColor(whiteColor)
                id_set_tv_black_text.setTextColor(whiteColor)
                id_set_radiobutton1.setTextColor(whiteColor)
                id_set_radiobutton2.setTextColor(whiteColor)
                id_set_radiobutton3.setTextColor(whiteColor)
                id_set_radiobutton4.setTextColor(whiteColor)
                id_set_button_menu.setTextColor(whiteColor)
            }
        }
    }

}