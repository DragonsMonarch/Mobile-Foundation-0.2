package com.dragonslotos.foundation.screens.posts

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dragonslotos.foundation.MainActivity
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.model.Post
import com.dragonslotos.foundation.screens.add_post.Add_Post
import com.dragonslotos.foundation.screens.table.Table
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import java.util.*


class Posts : Fragment() {
    private lateinit var adapter: PostsAdapter // Объект Adapter
    lateinit var postsViewModel:PostsViewModel
    var bundle = Table.bundles;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_posts, container, false)
        //inizialize
        postsViewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        val recyclerView: RecyclerView? = view?.findViewById<RecyclerView>(R.id.recyclerView);
        val search: TextInputEditText? = view?.findViewById<TextInputEditText>(R.id.search);
        val buttonUpdate: Button? = view?.findViewById<Button>(R.id.updateButton)
        //get Login and Password
        postsViewModel.Login.value = bundle?.getString("username")
        postsViewModel.Password.value = bundle?.getString("password")
        //Load Recycler view
        adapter = PostsAdapter();
        adapter.bundle = bundle;
        adapter.mContext = requireContext()
        postsViewModel.getFromeServeAll();
        postsViewModel.getFromeServeAll();
        postsViewModel.Posts.observe(viewLifecycleOwner, {
            if(postsViewModel.Posts.value != null){
                adapter.upData(postsViewModel.Posts.value as List<Post>)
            }
            else{
                adapter.upData(emptyList<Post>())
            }


        })
        val vba: FloatingActionButton? = view?.findViewById<FloatingActionButton>(R.id.floating_action_button)
        vba?.setOnClickListener{
            (context as MainActivity).changeFragment(Add_Post.newInstance(bundle))
        }
        search?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            Log.d("xui", keyCode.toString())
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                postsViewModel.PostName.value = search?.text.toString();
                postsViewModel.getFromeServeNamed();
                return@OnKeyListener true
            }
            false
        })
        buttonUpdate?.setOnClickListener {
            postsViewModel.getFromeServeAll()
        }
        recyclerView?.adapter = adapter

        return view
    }

    companion object{
        @JvmStatic
        fun newInstance() = Posts()
    }
}