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
import kotlinx.android.synthetic.main.fragment_identical_points.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_who_has_more.*
import kotlinx.coroutines.*

class IdenticalPointsFragment : Fragment() {

    private var job:Job = Job()

    var point1 = 0
    var point2 = 0

    private var result = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_identical_points, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        //загрузка фоновой картинки
        loadBackground()

        //загрузка моей аватарки и имени
        showAvatarAndNameMe()

        //начало игры
        id_identical_button_start.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                id_identical_button_start.isVisible = false
                id_identical_tv_name.isVisible = true
                loadRandomPoints()
                delay(2000)
                loadBackgroundImage(listUrlImageDice[point1-1],id_identical_iv_img_dice1)
                showToast(point1.toString())
                delay(1500)
                loadBackgroundImage(listUrlImageDice[point2-1],id_identical_iv_img_dice2)
                showToast(point2.toString())
                delay(1500)
                loadZnakSravneniya()
            }
        }

        id_identical_button_finish.setOnClickListener {
            goToGameOver()
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(job.isActive){
                job.cancel()
            }
            MAIN.navController.navigate(R.id.action_identicalPointsFragment_to_difficultGameFragment)
        }



    }

    //функция загрузки фоновой картинки
    private fun loadBackground(){
        when(MAIN.getMyNumberBackground()){
            1 -> { loadBackgroundImage(url_image_background1,id_identical_img) }
            2 -> { loadBackgroundImage(url_image_background2,id_identical_img) }
            3 -> { loadBackgroundImage(url_image_background3,id_identical_img) }
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
                id_identical_button_start.setTextColor(blackColor)
                id_identical_tv_title_game.setTextColor(blackColor)
                id_identical_button_finish.setTextColor(blackColor)
                id_identical_tv_name.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_identical_button_start.setTextColor(whiteColor)
                id_identical_tv_title_game.setTextColor(whiteColor)
                id_identical_button_finish.setTextColor(whiteColor)
                id_identical_tv_name.setTextColor(whiteColor)
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
        loadBackgroundImage(listSmile[MAIN.getMyNumberSmile()-1],id_identical_iv_img_me_avatar)
        id_identical_tv_name.text = MAIN.getMyName()
    }

    private fun loadRandomPoints(){
        point1 = listAllPoints.shuffled()[0]
        point2 = listAllPoints.shuffled()[0]
    }

    private fun loadZnakSravneniya(){
        if(point1>point2){
            loadBackgroundImage(url_image_identical_more,id_identical_iv_img_sravnenie)
            result = LOSS
        }else{
            if(point1<point2){
                loadBackgroundImage(url_image_identical_less,id_identical_iv_img_sravnenie)
                result = LOSS
            }else{
                loadBackgroundImage(url_image_identical_ravno,id_identical_iv_img_sravnenie)
                result = WIN
            }
        }
        id_identical_button_finish.isEnabled = true
    }

    private fun goToGameOver(){
        var bundle = Bundle()
        bundle.putString(RESULT,result)
        bundle.putString(GAME, GAME_IDENTICAL_POINTS)
        MAIN.navController.navigate(R.id.action_identicalPointsFragment_to_gameOverFragment,bundle)
    }


}