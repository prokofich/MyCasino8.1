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
import kotlinx.android.synthetic.main.fragment_difficult_game.*
import kotlinx.android.synthetic.main.fragment_free_cash.*
import kotlinx.android.synthetic.main.fragment_who_has_more.*

class DifficultGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_difficult_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        //загрузка фоновой картинки
        loadBackground()

        //переход на первую игру
        id_dif_button_3_dice.setOnClickListener {
            if(MAIN.getMyCash()>10){
                MAIN.navController.navigate(R.id.action_difficultGameFragment_to_whoHasMoreFragment)
                MAIN.minusCash(10)
            }else{
                showToast("you don't have enough funds")
            }
        }

        //переход на вторую игру
        id_dif_button_identical_points.setOnClickListener {
            if(MAIN.getMyCash()>20){
                MAIN.navController.navigate(R.id.action_difficultGameFragment_to_identicalPointsFragment)
                MAIN.minusCash(20)
            }else{
                showToast("you don't have enough funds")
            }
        }

        //переход на третью игру
        id_dif_button_guess_points.setOnClickListener {
            if(MAIN.getMyCash()>15){
                MAIN.navController.navigate(R.id.action_difficultGameFragment_to_guessPointsFragment)
                MAIN.minusCash(15)
            }else{
                showToast("you don't have enough funds")
            }
        }

        //переход обратно в меню
        id_dif_button_menu.setOnClickListener {
            MAIN.navController.navigate(R.id.action_difficultGameFragment_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_difficultGameFragment_to_menuFragment)
        }



    }

    //функция загрузки фоновой картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    //функция загрузки фоновой картинки
    private fun loadBackground(){
        when(MAIN.getMyNumberBackground()){
            1 -> { loadBackgroundImage(url_image_background1,id_dif_img) }
            2 -> { loadBackgroundImage(url_image_background2,id_dif_img) }
            3 -> { loadBackgroundImage(url_image_background3,id_dif_img) }
        }
    }

    //функция замены цвета текста в вьюхах
    @SuppressLint("ResourceAsColor")
    private fun changeViewColorText(){
        when(MAIN.getMyColorText()){
            BLACK_TEXT -> {
                val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
                id_dif_button_3_dice.setTextColor(blackColor)
                id_dif_button_identical_points.setTextColor(blackColor)
                id_dif_button_guess_points.setTextColor(blackColor)
                id_dif_button_menu.setTextColor(blackColor)
                id_dif_price_win_3dice.setTextColor(blackColor)
                id_dif_identical_points.setTextColor(blackColor)
                id_dif_guess_points.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_dif_button_3_dice.setTextColor(whiteColor)
                id_dif_button_identical_points.setTextColor(whiteColor)
                id_dif_button_guess_points.setTextColor(whiteColor)
                id_dif_button_menu.setTextColor(whiteColor)
                id_dif_price_win_3dice.setTextColor(whiteColor)
                id_dif_identical_points.setTextColor(whiteColor)
                id_dif_guess_points.setTextColor(whiteColor)
            }
        }
    }


}