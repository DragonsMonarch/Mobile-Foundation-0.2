package com.dragonslotos.foundation.screens.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.dragonslotos.foundation.MainActivity
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.screens.login.login
import com.dragonslotos.foundation.screens.table.Table
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKAuthResult
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.utils.VKUtils.getCertificateFingerprint


class registered : Fragment() {
    lateinit var registeredViewModel: RegisteredViewModel
    private lateinit var token: VKAccessToken

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registeredViewModel = ViewModelProvider(this).get(RegisteredViewModel::class.java)
        val view: View? = inflater.inflate(R.layout.fragment_registered, container, false)
        val confrim: MaterialButton? = view?.findViewById<MaterialButton>(R.id.confrim)

        val LoginLayout: TextInputLayout? = view?.findViewById<TextInputLayout>(R.id.loginLayout);
        val Login: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.login)

        val Password: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.pass)
        val ConfrimPassword: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.conpass)

        val PasswordLayout: TextInputLayout? = view?.findViewById<TextInputLayout>(R.id.password)
        val ConfrimPasswordLayout: TextInputLayout? = view?.findViewById<TextInputLayout>(R.id.confrim_password)

        val Exit: ImageButton? = view?.findViewById<ImageButton>(R.id.goToLogin)

        val VKfrom: MaterialButton? = view?.findViewById<MaterialButton>(R.id.with_vk)
        VKfrom?.setOnClickListener {

            VK.login(requireActivity(), arrayListOf(VKScope.WALL, VKScope.PHOTOS))
            registeredViewModel.vkRegistration();

        }
        confrim?.setOnClickListener {
            if(Password?.text.toString().length < 7 ){
                PasswordLayout?.error = "Пароль должен состоять от 8 знаков"
                PasswordLayout?.isErrorEnabled = true
                return@setOnClickListener
            }
            if(!Password?.text.toString().equals(ConfrimPassword?.text.toString())){
                PasswordLayout?.error = "Пароли не совпадают"
                ConfrimPasswordLayout?.error = "Пароли не совпадают"
                PasswordLayout?.isErrorEnabled = true
                ConfrimPasswordLayout?.isErrorEnabled = true
                return@setOnClickListener
            }
            PasswordLayout?.isErrorEnabled = false
            ConfrimPasswordLayout?.isErrorEnabled = false
            registeredViewModel.Login.value = Login?.text.toString()
            registeredViewModel.Password.value = Password?.text.toString()
            registeredViewModel.startUserRegistration();
        }

        registeredViewModel.Checking.observe(viewLifecycleOwner, {
            if(it == false){
                LoginLayout?.error = "Такой логин уже есть"
                LoginLayout?.isErrorEnabled = true;
            }
            else{
                LoginLayout?.isErrorEnabled = false;
            }
        })

        registeredViewModel.Logging.observe(viewLifecycleOwner, {
            if(it == true){
                var bundles = Bundle()
                bundles.putString("username", registeredViewModel.Login.value.toString())
                bundles.putString("password", registeredViewModel.Password.value.toString())
                (context as MainActivity).changeFragment(Table.newInstance(bundles))
            }
        })

        Exit?.setOnClickListener {
            (context as MainActivity).changeFragment(login.newInstance())
        }

        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
    }
    companion object{
        @JvmStatic
        fun newInstance() = registered()
    }

}