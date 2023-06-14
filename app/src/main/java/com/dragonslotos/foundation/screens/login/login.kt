package com.dragonslotos.foundation.screens.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dragonslotos.foundation.MainActivity
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.screens.registration.RegisteredViewModel
import com.dragonslotos.foundation.screens.registration.registered
import com.dragonslotos.foundation.screens.table.Table
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

class login : Fragment() {
    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Грузим великую ViewModel
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //Получаем ссылки на все необходимые view
        val view: View? = inflater.inflate(R.layout.login, container, false)

        val Login_Layout: TextInputLayout? = view?.findViewById<TextInputLayout>(R.id.login_layout)
        val Login_Edit: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.login_edit)

        val Password_Layout: TextInputLayout? = view?.findViewById<TextInputLayout>(R.id.password_layout)
        val Password_Edit: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.password_edit)

        val Enter_Login: MaterialButton? = view?.findViewById<MaterialButton>(R.id.enter_login)
        val toRegistration: MaterialButton? = view?.findViewById<MaterialButton>(R.id.registration)
        val vk_login: MaterialButton? = view?.findViewById<MaterialButton>(R.id.vk_login)

        //Обработчики нажатий
        Enter_Login?.setOnClickListener {
            //Проверяем заполнен ли логин
            if(Login_Edit?.text.toString() == ""){
                Login_Layout?.error = "Поле \"Login\" должно быть заполнено"
                Login_Layout?.isErrorEnabled = true
                return@setOnClickListener
            }
            Login_Layout?.isErrorEnabled = false;
            //Заполнен пароль
            if(Password_Edit?.text.toString() == ""){
                Password_Layout?.error = "Поле \"Password\" должно быть заполнено"
                Password_Layout?.isErrorEnabled = true;
                return@setOnClickListener
            }
            //Добавляем логнин и пассворд в о великую viewmodel
            //Переходим в неё
            Password_Layout?.isErrorEnabled = false
            loginViewModel.Login.value = Login_Edit?.text.toString()
            loginViewModel.Password.value = Password_Edit?.text.toString()
            loginViewModel.enterSystem();
        }

        //Логинимся через вк
        vk_login?.setOnClickListener {
            VK.login(requireActivity(), arrayListOf(VKScope.WALL, VKScope.PHOTOS))
            loginViewModel.vkLogin()
        }

        //О виликая viewmodel, да поможет нам MutableData, чтобы слушать ваши истины
        //Проверяем произошла ли ошибка со стороны сервера
        loginViewModel.LoggingError.observe(viewLifecycleOwner, {
            if(it == true){
                Login_Layout?.error = "Неверный логин или пароль"
                Login_Layout?.isErrorEnabled = true
            }
            else {
                Login_Layout?.isErrorEnabled = false
            }
        })
        //О виликая viewmodel, да поможет нам MutableData, чтобы слушать ваши истины
        //Проверяем дано ли разрешение на вход
        loginViewModel.EnterSystem.observe(viewLifecycleOwner, {
            if(it == true){
                //Грузим нужные данные в бандл и идём дальше
                var bundles = Bundle()
                bundles.putString("username", loginViewModel.Login.value.toString())
                bundles.putString("password", loginViewModel.Password.value.toString())
                (context as MainActivity).changeFragment(Table.newInstance(bundles))
            }
        })
        //Переходим в регистрационный фрагшмент
        //Внимание всем пассажирам без аккаунта перейти на платформу регистрации
        toRegistration?.setOnClickListener {
            (context as MainActivity).changeFragment(registered.newInstance())
        }

        return view
    }

    //Инстансы
    companion object{
        @JvmStatic
        fun newInstance() = login()
    }
}