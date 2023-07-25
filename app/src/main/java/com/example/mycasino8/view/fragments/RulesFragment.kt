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
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino8.R
import com.example.mycasino8.constant.*
import com.example.mycasino8.viewmodel.RulesViewModel
import kotlinx.android.synthetic.main.fragment_rules.*
import kotlinx.android.synthetic.main.fragment_settings.*


class RulesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rules, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //загрузка цвета текста
        changeViewColorText()

        //загрузка фоновой картинки
        loadBackground()

        //загрузка текста правил для игр
        val rulesViewModel = ViewModelProvider(this)[RulesViewModel::class.java]
        rulesViewModel.getTextRulesGame()
        rulesViewModel.Text.observe(viewLifecycleOwner){ TEXT ->
            id_rules_tv_rules1.text = TEXT.body()!![0].text
            id_rules_tv_rules2.text = TEXT.body()!![1].text
            id_rules_tv_rules3.text = TEXT.body()!![2].text
        }

        //выход в меню
        id_rules_button_finish.setOnClickListener {
            MAIN.navController.navigate(R.id.action_rulesFragment_to_menuFragment)
        }

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            MAIN.navController.navigate(R.id.action_rulesFragment_to_menuFragment)
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
            1 -> { loadBackgroundImage(url_image_background1,id_rules_img) }
            2 -> { loadBackgroundImage(url_image_background2,id_rules_img) }
            3 -> { loadBackgroundImage(url_image_background3,id_rules_img) }
        }
    }

    //функция замены цвета текста в вьюхах
    @SuppressLint("ResourceAsColor")
    private fun changeViewColorText(){
        when(MAIN.getMyColorText()){
            BLACK_TEXT -> {
                val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
                id_rules_tv_title_rules.setTextColor(blackColor)
                id_tv_title_rules1.setTextColor(blackColor)
                id_tv_title_rules2.setTextColor(blackColor)
                id_tv_title_rules3.setTextColor(blackColor)
                id_rules_tv_rules1.setTextColor(blackColor)
                id_rules_tv_rules2.setTextColor(blackColor)
                id_rules_tv_rules3.setTextColor(blackColor)
                id_rules_button_finish.setTextColor(blackColor)
            }
            WHITE_TEXT -> {
                val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
                id_rules_tv_title_rules.setTextColor(whiteColor)
                id_tv_title_rules1.setTextColor(whiteColor)
                id_tv_title_rules2.setTextColor(whiteColor)
                id_tv_title_rules3.setTextColor(whiteColor)
                id_rules_tv_rules1.setTextColor(whiteColor)
                id_rules_tv_rules2.setTextColor(whiteColor)
                id_rules_tv_rules3.setTextColor(whiteColor)
                id_rules_button_finish.setTextColor(whiteColor)
            }
        }
    }


}