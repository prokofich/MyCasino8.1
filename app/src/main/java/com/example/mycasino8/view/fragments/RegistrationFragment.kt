package com.example.mycasino8.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.addCallback
import com.bumptech.glide.Glide
import com.example.mycasino8.R
import com.example.mycasino8.constant.FEMALE
import com.example.mycasino8.constant.MAIN
import com.example.mycasino8.constant.MALE
import com.example.mycasino8.constant.url_image_background_registration
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.coroutines.*

class RegistrationFragment : Fragment() {

    private var sex = ""
    private var job:Job = Job()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(MAIN.getMyName()!=""){
            MAIN.navController.navigate(R.id.action_registrationFragment_to_freeCashFragment)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //обработка нажатия на кнопку НАЗАД
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            if(job.isActive){
                job.cancel()
            }
            MAIN.finishAffinity()
        }

        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background_registration,id_reg_img)

        //обработка выбора мужского пола
        id_reg_radiobutton_male.setOnCheckedChangeListener { button , isChecked ->
            if (isChecked) {
                sex = MALE
                id_reg_radiobutton_female.isChecked = false
            } else {
                sex = FEMALE
            }
        }

        //обработка выбора женского пола
        id_reg_radiobutton_female.setOnCheckedChangeListener { button , isChecked ->
            if (isChecked) {
                sex = FEMALE
                id_reg_radiobutton_male.isChecked = false
            } else {
                sex = FEMALE
            }
        }

        //обработка нажатия на кнопку завершения регистрации
        id_reg_button_finish.setOnClickListener {
            id_reg_button_finish.isEnabled = false
            job = CoroutineScope(Dispatchers.Main).launch {
                if(id_reg_et_name.text.isNotEmpty()){
                    if(sex!=""){
                        showToast("successfully!")
                        MAIN.setMyName(id_reg_et_name.text.toString())
                        MAIN.setMySex(sex)
                        delay(1000)
                        MAIN.navController.navigate(R.id.action_registrationFragment_to_freeCashFragment)
                    }else{
                        id_reg_button_finish.isEnabled = false
                        showToast("you haven't chosen your gender")
                    }
                }else{
                    id_reg_button_finish.isEnabled = false
                    showToast("enter your name")
                }
            }
        }


    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id:ImageView){
        Glide.with(requireContext())
            .load(url)
            .into(id)
    }

    //функция показа всплывающего сообщения
    private fun showToast(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

}