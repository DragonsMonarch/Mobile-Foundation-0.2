package com.dragonslotos.foundation.screens.add_post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dragonslotos.foundation.MainActivity
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.screens.posts.PostsViewModel
import com.dragonslotos.foundation.screens.table.Table
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Add_Post(bundle: Bundle) : Fragment() {
    var bundle1 = bundle;
    lateinit var addViewModel: AddViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add__post, container, false)
        // Inflate the layout for this fragment
        addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        val createPost: MaterialButton? = view?.findViewById<MaterialButton>(R.id.createpost)
        val name: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.post_name_edit)
        val body: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.post_text_edit)
        addViewModel.Login.value = bundle1?.getString("username")
        addViewModel.Password.value = bundle1?.getString("password")
        createPost?.setOnClickListener {
            addViewModel.loginInSever(name?.text.toString(),body?.text.toString())
            (context as MainActivity).changeFragment(Table.newInstance(bundle1))
        }
        return view
    }
    companion object{
        @JvmStatic
        fun newInstance(bundle: Bundle) = Add_Post(bundle)
    }

}