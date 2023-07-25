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
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_who_has_more.*
import kotlinx.coroutines.*


class WhoHasMoreFragment : Fragment() {

    private var myListPoints = mutableListOf<Int>()
    private var opListPoints = mutableListOf<Int>()

    private var result = ""

    private var job:Job = Job()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_who_has_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        //загрузка фоновой картинки
        loadBackground()

        //показ всех имен и аватарок
        showAvatarAndNameMe()
        showAvatarAndNameOpponent()

        //начало игры
        id_3dice_button_start.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                id_3dice_button_start.isVisible = false
                loadOpRandomPoints()
                loadMeRandomPoints()
                delay(1000)
                loadBackgroundImage(listUrlImageDice[opListPoints[0]-1],id_3dice_iv_img_op1)
                id_3dice_tv_points_opponent.text = opListPoints[0].toString()
                delay(800)
                loadBackgroundImage(listUrlImageDice[opListPoints[1]-1],id_3dice_iv_img_op2)
                id_3dice_tv_points_opponent.text = opListPoints[0].toString() + "+" + opListPoints[1]
                delay(800)
                loadBackgroundImage(listUrlImageDice[opListPoints[2]-1],id_3dice_iv_img_op3)
                id_3dice_tv_points_opponent.text = opListPoints[0].toString()+"+"+opListPoints[1]+"+"+opListPoints[2]+"=" + " ${opListPoints.sum()}"
                delay(800)
                showToast("the opponent has ${opListPoints.sum()} points")
                delay(800)
                loadBackgroundImage(listUrlImageDice[myListPoints[0]-1],id_3dice_iv_img_me1)
                id_3dice_tv_points_me.text = myListPoints[0].toString()
                delay(800)
                loadBackgroundImage(listUrlImageDice[myListPoints[1]-1],id_3dice_iv_img_me2)
                id_3dice_tv_points_me.text = myListPoints[0].toString() + "+" + myListPoints[1]
                delay(800)
                loadBackgroundImage(listUrlImageDice[myListPoints[2]-1],id_3dice_iv_img_me3)
                id_3dice_tv_points_me.text = myListPoints[0].toString()+"+"+myListPoints[1]+"+"+myListPoints[2]+"="+" ${myListPoints.sum()}"
                delay(800)
                showToast("you have ${myListPoints.sum()} points")
                delay(800)
                checkResultGame()
                //delay(2000)
                //goToGameOver()
            }

        }

        id_3dice_button_finish.setOnClickListener {
            if(job.isActive){
                job.cancel()
            }
            goToGameOver()
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(job.isActive){
                job.cancel()
            }
            MAIN.navController.navigate(R.id.action_whoHasMoreFragment_to_difficultGameFragment)
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

    //функция загрузки фоновой картинки
    private fun loadBackground(){
        when(MAIN.getMyNumberBackground()){
            1 -> { loadBackgroundImage(url_image_background1,id_3dice_img) }
            2 -> { loadBackgroundImage(url_image_background2,id_3dice_img) }
            3 -> { loadBackgroundImage(url_image_background3,id_3dice_img) }
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
        loadBackgroundImage(listSmile[MAIN.getMyNumberSmile()-1],id_3dice_iv_img_me)
        id_3dice_tv_name_me.text = MAIN.getMyName()

    }

    //функция показа аватарки и имени соперника
    private fun showAvatarAndNameOpponent(){
        val list = listOf(MALE, FEMALE)
        var listSmile = emptyList<String>()
        var listName = emptyList<String>()
        when(list.shuffled()[0]){
            MALE -> {
                listName = listNameMale
                listSmile = listUrlImagesSmileMale
            }
            FEMALE -> {
                listName = listNameFemale
                listSmile = listUrlImagesSmileFemale
            }
        }
        loadBackgroundImage(listSmile.shuffled()[0],id_3dice_iv_img_opponent)
        id_3dice_tv_name_opponent.text = listName.shuffled()[0]
    }

    private fun loadMeRandomPoints(){
        myListPoints.addAll(listAllPoints.shuffled().slice(0..2))
    }

    private fun loadOpRandomPoints(){
        opListPoints.addAll(listAllPoints.shuffled().slice(0..2))
    }

    private fun checkResultGame(){
        if(myListPoints.sum()>opListPoints.sum()){
            showToast("you have won!!!")
            id_3dice_tv_title_game.text = "victory!"
        }else{
            showToast("you've lost...")
            id_3dice_tv_title_game.text = "loss..."
        }
        id_3dice_button_finish.isEnabled = true
    }

    private fun goToGameOver(){
        var bundle = Bundle()
        if(myListPoints.sum()>opListPoints.sum()){
            bundle.putString(RESULT, WIN)
        }else{
            bundle.putString(RESULT, LOSS)
        }
        bundle.putString(GAME, GAME_3DICE)
        MAIN.navController.navigate(R.id.action_whoHasMoreFragment_to_gameOverFragment,bundle)
    }

    //функция замены цвета текста в вьюхах
    @SuppressLint("ResourceAsColor")
    private fun changeViewColorText(){
        when(MAIN.getMyColorText()){
            BLACK_TEXT -> {
                val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
                id_3dice_tv_title_game.setTextColor(blackColor)
                id_3dice_tv_name_me.setTextColor(blackColor)
                id_3dice_tv_points_me.setTextColor(blackColor)
                id_3dice_button_start.setTextColor(blackColor)
                id_3dice_tv_name_opponent.setTextColor(blackColor)
                id_3dice_tv_points_opponent.setTextColor(blackColor)
                id_3dice_button_finish.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_3dice_tv_title_game.setTextColor(whiteColor)
                id_3dice_tv_name_me.setTextColor(whiteColor)
                id_3dice_tv_points_me.setTextColor(whiteColor)
                id_3dice_button_start.setTextColor(whiteColor)
                id_3dice_tv_name_opponent.setTextColor(whiteColor)
                id_3dice_tv_points_opponent.setTextColor(whiteColor)
                id_3dice_button_finish.setTextColor(whiteColor)
            }
        }
    }



}