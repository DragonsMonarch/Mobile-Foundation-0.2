package com.dragonslotos.foundation.screens.post

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.dragonslotos.foundation.MainActivity
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.screens.profile.ProfileViewModel
import com.dragonslotos.foundation.screens.table.Table

class  Post(bundle: Bundle) : Fragment() {
    lateinit var postViewModel: PostViewModel
    val bundle1 = bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        postViewModel.Login.value = bundle1?.getString("username")
        postViewModel.Password.value = bundle1?.getString("password")
        postViewModel.getLikeFromeServeAll()
        postViewModel.Name.value = bundle1.getString("PostName")
        val body: TextView? = view?.findViewById<TextView>(R.id.body)
        val name: TextView? = view?.findViewById<TextView>(R.id.postName)
        val time: TextView? = view?.findViewById<TextView>(R.id.Date)
        val owners: TextView? = view?.findViewById<TextView>(R.id.Owner)
        val returnButton: Button? = view?.findViewById<Button>(R.id.returnButton)
        val Likebutton: Button? = view?.findViewById<Button>(R.id.likeButton)
        postViewModel.Like.observe(viewLifecycleOwner,{
            if(postViewModel.Like.value == true){
                Likebutton?.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favourit)
            }

        })
        Likebutton?.setOnClickListener{
            if (postViewModel.Like.value == true){
                postViewModel.Like.value = false
                Likebutton?.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favourit_border)
                postViewModel.disLike(bundle1.getString("PostName"), bundle1.getString("PostBody"));
            }
            else{
                postViewModel.Like.value =true
                Likebutton?.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_favourit)
                postViewModel.setLike(bundle1.getString("PostName"), bundle1.getString("PostBody"));
            }
        }
        body?.text = bundle1.getString("PostBody")
        name?.text = bundle1.getString("PostName")
        time?.text = bundle1.getString("PostDate")
        owners?.text = bundle1.getString("PostOwner")
        body?.movementMethod = ScrollingMovementMethod()
        returnButton?.setOnClickListener{
            (context as MainActivity).changeFragment(Table.newInstance(bundle1))
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) = Post(bundle)
    }
}