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
import kotlinx.android.synthetic.main.fragment_game_over.*
import kotlinx.android.synthetic.main.fragment_menu.*

class GameOverFragment : Fragment() {

    private var resultGame = ""
    private var winCash = 0
    private var game = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_over, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        resultGame = requireArguments().getString(RESULT)!!
        game = requireArguments().getString(GAME)!!

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_gameOverFragment_to_difficultGameFragment)
        }

        //загрузка картинки смайлика и надписей
        when(resultGame){
            WIN -> {
                loadBackgroundImage(url_image_smile_win,id_gameover_iv_img_smile)
                id_gameover_tv_result.text = "win!win!win!"
                when(game){
                    GAME_3DICE -> {
                        winCash = 20
                        MAIN.addCash(20)
                        id_gameover_tv_cash.text = "you have earned 20$"
                    }
                    GAME_IDENTICAL_POINTS -> {
                        winCash = 50
                        MAIN.addCash(50)
                        id_gameover_tv_cash.text = "you have earned 50$"
                    }
                    GAME_GUESS_POINTS -> {
                        winCash = 50
                        MAIN.addCash(50)
                        id_gameover_tv_cash.text = "you have earned 50$"
                    }
                }
            }
            LOSS -> {
                loadBackgroundImage(url_image_smile_loss,id_gameover_iv_img_smile)
                id_gameover_tv_result.text = "you've lost..."
                when(game){
                    GAME_3DICE -> { id_gameover_tv_cash.text = "you have lost 10$" }
                    GAME_IDENTICAL_POINTS -> { id_gameover_tv_cash.text = "you have lost 20$" }
                    GAME_GUESS_POINTS -> { id_gameover_tv_cash.text = "you have lost 15$" }
                }
            }
        }

        //переход в меню
        id_gameover_button_menu.setOnClickListener {
            MAIN.navController.navigate(R.id.action_gameOverFragment_to_menuFragment)
        }

        //рестарт игры
        id_gameover_button_restart.setOnClickListener {
            when(game){
                GAME_3DICE -> {
                    if(MAIN.getMyCash()>10){
                        MAIN.minusCash(10)
                        MAIN.navController.navigate(R.id.action_gameOverFragment_to_whoHasMoreFragment)
                    }else{
                        showToast("you don't have enough funds")
                    }

                }
                GAME_IDENTICAL_POINTS -> {
                    if(MAIN.getMyCash()>20){
                        MAIN.minusCash(20)
                        MAIN.navController.navigate(R.id.action_gameOverFragment_to_identicalPointsFragment)
                    }else{
                        showToast("you don't have enough funds")
                    }
                }
                GAME_GUESS_POINTS -> {
                    if(MAIN.getMyCash()>15){
                        MAIN.minusCash(15)
                        MAIN.navController.navigate(R.id.action_gameOverFragment_to_guessPointsFragment)
                    }else{
                        showToast("you don't have enough funds")
                    }
                }
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
                id_gameover_tv_title.setTextColor(blackColor)
                id_gameover_tv_result.setTextColor(blackColor)
                id_gameover_tv_cash.setTextColor(blackColor)
                id_gameover_button_restart.setTextColor(blackColor)
                id_gameover_button_menu.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_gameover_tv_title.setTextColor(whiteColor)
                id_gameover_tv_result.setTextColor(whiteColor)
                id_gameover_tv_cash.setTextColor(whiteColor)
                id_gameover_button_restart.setTextColor(whiteColor)
                id_gameover_button_menu.setTextColor(whiteColor)
            }
        }
    }

}