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

class login : Fragment() {
    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val view: View? = inflater.inflate(R.layout.login, container, false)

        val Login_Layout: TextInputLayout? = view?.findViewById<TextInputLayout>(R.id.login_layout)
        val Login_Edit: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.login_edit)

        val Password_Layout: TextInputLayout? = view?.findViewById<TextInputLayout>(R.id.password_layout)
        val Password_Edit: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.password_edit)

        val Enter_Login: MaterialButton? = view?.findViewById<MaterialButton>(R.id.enter_login)
        val toRegistration: MaterialButton? = view?.findViewById<MaterialButton>(R.id.registration)

        Enter_Login?.setOnClickListener {
            if(Login_Edit?.text.toString() == ""){
                Login_Layout?.error = "Поле \"Login\" должно быть заполнено"
                Login_Layout?.isErrorEnabled = true
                return@setOnClickListener
            }
            Login_Layout?.isErrorEnabled = false;
            if(Password_Edit?.text.toString() == ""){
                Password_Layout?.error = "Поле \"Password\" должно быть заполнено"
                Password_Layout?.isErrorEnabled = true;
                return@setOnClickListener
            }
            Password_Layout?.isErrorEnabled = false
            loginViewModel.Login.value = Login_Edit?.text.toString()
            loginViewModel.Password.value = Password_Edit?.text.toString()
            loginViewModel.enterSystem();
        }
        loginViewModel.LoggingError.observe(viewLifecycleOwner, {
            if(it == true){
                Login_Layout?.error = "Неверный логин или пароль"
                Login_Layout?.isErrorEnabled = true
            }
            else {
                Login_Layout?.isErrorEnabled = false
            }
        })
        loginViewModel.EnterSystem.observe(viewLifecycleOwner, {
            if(it == true){
                var bundles = Bundle()
                bundles.putString("username", loginViewModel.Login.value.toString())
                bundles.putString("password", loginViewModel.Password.value.toString())
                (context as MainActivity).changeFragment(Table.newInstance(bundles))
            }
        })
        toRegistration?.setOnClickListener {
            (context as MainActivity).changeFragment(registered.newInstance())
        }

        return view
    }
    companion object{
        @JvmStatic
        fun newInstance() = login()
    }
}