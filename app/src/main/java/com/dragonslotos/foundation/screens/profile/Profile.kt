package com.dragonslotos.foundation.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.model.Post
import com.dragonslotos.foundation.screens.table.Table

class Profile : Fragment() {
    private lateinit var adapter: PostOwnAdapter
    private lateinit var Like: LikeProfileAdapter
    var bundle = Table.bundles;
    lateinit var profileViewModel:ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_porfile, container, false)
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val recyclerView: RecyclerView? = view?.findViewById<RecyclerView>(R.id.OwnerPosts)
        val recyclerViewlike: RecyclerView? = view?.findViewById<RecyclerView>(R.id.favouritPost)
        val name: TextView? = view?.findViewById<TextView>(R.id.username)
        profileViewModel.Login.value = bundle?.getString("username")
        profileViewModel.Password.value = bundle?.getString("password")
        name?.text = profileViewModel.Login.value.toString()
        profileViewModel.getFromeServeAll()
        adapter = PostOwnAdapter();
        adapter.bundle = bundle;
        adapter.mContext = requireContext()
        Like = LikeProfileAdapter();
        Like.bundle = bundle;
        Like.mContext = requireContext()
        profileViewModel.getFromeServeAll();
        profileViewModel.getFromeServeAll();
        profileViewModel.getLikeFromeServeAll();
        profileViewModel.getLikeFromeServeAll();
        profileViewModel.Posts.observe(viewLifecycleOwner, {
            if(profileViewModel.Posts.value != null){
                adapter.upData(profileViewModel.Posts.value as List<Post>)
            }
            else{
                adapter.upData(emptyList<Post>())
            }


        })
        profileViewModel.PostsLike.observe(viewLifecycleOwner, {
            if(profileViewModel.Posts.value != null){
                Like.upData(profileViewModel.PostsLike.value as List<Post>)
            }
            else{
                Like.upData(emptyList<Post>())
            }


        })

        recyclerView?.adapter = adapter
        recyclerViewlike?.adapter = Like
        return view
    }
    companion object{
        @JvmStatic
        fun newInstance() = Profile()
    }
}