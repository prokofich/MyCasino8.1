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
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.mycasino8.R
import com.example.mycasino8.constant.*
import kotlinx.android.synthetic.main.fragment_guess_points.*
import kotlinx.android.synthetic.main.fragment_identical_points.*
import kotlinx.android.synthetic.main.fragment_who_has_more.*
import kotlinx.coroutines.*


class GuessPointsFragment : Fragment() {

    private var result = ""
    private var job: Job = Job()

    private var point1 = 0
    private var point2 = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guess_points, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        //загрузка фоновой картинки
        loadBackground()

        //загрущка аватарки и имени
        showAvatarAndNameMe()

        //начало игры
        id_guess_button_start.setOnClickListener {
            id_guess_button_start.isVisible = false
            id_guess_tv_points.isVisible = true
            showToast("guess the outcome and click on the button")
            allButtonEnable(true)
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(job.isActive){
                job.cancel()
            }
            MAIN.navController.navigate(R.id.action_guessPointsFragment_to_difficultGameFragment)
        }

        id_guess_button_points1.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                id_guess_button_points1.setBackgroundResource(R.drawable.background_ll_menu1)
                allButtonEnable(false)
                loadRandomPoints()
                delay(2000)
                loadDice()
                delay(2000)
                checkResult(1)
            }
        }

        id_guess_button_points2.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                id_guess_button_points2.setBackgroundResource(R.drawable.background_ll_menu1)
                allButtonEnable(false)
                loadRandomPoints()
                delay(2000)
                loadDice()
                delay(2000)
                checkResult(2)
            }
        }

        id_guess_button_points3.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                id_guess_button_points3.setBackgroundResource(R.drawable.background_ll_menu1)
                allButtonEnable(false)
                loadRandomPoints()
                delay(2000)
                loadDice()
                delay(2000)
                checkResult(3)
            }
        }

        id_guess_button_points4.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                id_guess_button_points4.setBackgroundResource(R.drawable.background_ll_menu1)
                allButtonEnable(false)
                loadRandomPoints()
                delay(2000)
                loadDice()
                delay(2000)
                checkResult(4)
            }
        }

        id_guess_button_points5.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                id_guess_button_points5.setBackgroundResource(R.drawable.background_ll_menu1)
                allButtonEnable(false)
                loadRandomPoints()
                delay(2000)
                loadDice()
                delay(2000)
                checkResult(5)
            }
        }

        id_guess_button_points6.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                id_guess_button_points6.setBackgroundResource(R.drawable.background_ll_menu1)
                allButtonEnable(false)
                loadRandomPoints()
                delay(2000)
                loadDice()
                delay(2000)
                checkResult(6)
            }
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(job.isActive){
                job.cancel()
            }
            MAIN.navController.navigate(R.id.action_guessPointsFragment_to_difficultGameFragment)
        }


        id_guess_button_finish.setOnClickListener {
            goToGameOver()
        }



    }

    private fun loadRandomPoints(){
        point1 = listAllPoints.shuffled()[0]
        point2 = listAllPoints.shuffled()[0]
    }

    private fun checkResult(numberButton:Int){
        when(numberButton){
            1 -> {
                if(point1+point2 in 2..3){
                    result = WIN
                    showToast("WIN!")
                }else{
                    result = LOSS
                    showToast("loss...")
                }
            }
            2 -> {
                if(point1+point2 in 4..5){
                    result = WIN
                    showToast("WIN!")
                }else{
                    result = LOSS
                    showToast("loss...")
                }
            }
            3 -> {
                if(point1+point2 in 6..7){
                    result = WIN
                    showToast("WIN!")
                }else{
                    result = LOSS
                    showToast("loss...")
                }
            }
            4 -> {
                if(point1+point2 in 8..9){
                    result = WIN
                    showToast("WIN!")
                }else{
                    result = LOSS
                    showToast("loss...")
                }
            }
            5 -> {
                if(point1+point2 == 10 ){
                    result = WIN
                    showToast("WIN!")
                }else{
                    result = LOSS
                    showToast("loss...")
                }
            }
            6 -> {
                if(point1+point2 in 11..12){
                    result = WIN
                    showToast("WIN!")
                }else{
                    result = LOSS
                    showToast("loss...")
                }
            }
        }
        id_guess_button_finish.isEnabled = true
    }

    private fun loadDice(){
        loadBackgroundImage(listUrlImageDice[point1-1],id_guess_iv_img_dice1)
        loadBackgroundImage(listUrlImageDice[point2-1],id_guess_iv_img_dice2)
        id_guess_tv_points.text = point1.toString()+"+"+point2.toString()+"="+"${point2+point1}"
    }

    //функция замены цвета текста в вьюхах
    @SuppressLint("ResourceAsColor")
    private fun changeViewColorText(){
        when(MAIN.getMyColorText()){
            BLACK_TEXT -> {
                val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
                id_guess_button_start.setTextColor(blackColor)
                id_guess_tv_title_game.setTextColor(blackColor)
                id_guess_tv_name_me.setTextColor(blackColor)
                id_guess_tv_points.setTextColor(blackColor)
                id_guess_button_points1.setTextColor(blackColor)
                id_guess_button_points2.setTextColor(blackColor)
                id_guess_button_points3.setTextColor(blackColor)
                id_guess_button_points4.setTextColor(blackColor)
                id_guess_button_points5.setTextColor(blackColor)
                id_guess_button_points6.setTextColor(blackColor)
                id_guess_button_finish.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_guess_button_start.setTextColor(whiteColor)
                id_guess_tv_title_game.setTextColor(whiteColor)
                id_guess_tv_name_me.setTextColor(whiteColor)
                id_guess_tv_points.setTextColor(whiteColor)
                id_guess_button_points1.setTextColor(whiteColor)
                id_guess_button_points2.setTextColor(whiteColor)
                id_guess_button_points3.setTextColor(whiteColor)
                id_guess_button_points4.setTextColor(whiteColor)
                id_guess_button_points5.setTextColor(whiteColor)
                id_guess_button_points6.setTextColor(whiteColor)
                id_guess_button_finish.setTextColor(whiteColor)
            }
        }
    }

    //функция показа моей аватарки и имени
    private fun showAvatarAndNameMe(){
        var listSmile = emptyList<String>()
        when(MAIN.getMySex()){
            MALE -> {
                listSmile = listUrlImagesSmileMale
            }
            FEMALE -> {
                listSmile = listUrlImagesSmileFemale
            }
        }
        loadBackgroundImage(listSmile[MAIN.getMyNumberSmile()-1],id_guess_iv_img_me)
        id_guess_tv_name_me.text = MAIN.getMyName()
    }

    private fun goToGameOver(){
        var bundle = Bundle()
        bundle.putString(RESULT,result)
        bundle.putString(GAME, GAME_GUESS_POINTS)
        MAIN.navController.navigate(R.id.action_guessPointsFragment_to_gameOverFragment,bundle)
    }

    //функция загрузки фоновой картинки
    private fun loadBackground(){
        when(MAIN.getMyNumberBackground()){
            1 -> { loadBackgroundImage(url_image_background1,id_guesspoints_img) }
            2 -> { loadBackgroundImage(url_image_background2,id_guesspoints_img) }
            3 -> { loadBackgroundImage(url_image_background3,id_guesspoints_img) }
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
        Toast.makeText(requireContext(),message, Toast.LENGTH_LONG).show()
    }

    private fun allButtonEnable(bol:Boolean){
        id_guess_button_points1.isEnabled = bol
        id_guess_button_points2.isEnabled = bol
        id_guess_button_points3.isEnabled = bol
        id_guess_button_points4.isEnabled = bol
        id_guess_button_points5.isEnabled = bol
        id_guess_button_points6.isEnabled = bol
    }

}

