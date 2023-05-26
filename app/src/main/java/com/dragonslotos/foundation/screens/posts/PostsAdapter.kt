package com.dragonslotos.foundation.screens.posts


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.dragonslotos.foundation.MainActivity
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.model.Post
import com.google.android.material.card.MaterialCardView


class PostsAdapter: RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    var data: List<Post> = emptyList();
    lateinit var bundle: Bundle
    lateinit var mContext: Context
    class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    public fun upData(list:List<Post>){
        data = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(com.dragonslotos.foundation.R.layout.item_post_layout, parent, false)

        return PostsViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val Text = holder.itemView.findViewById<TextView>(com.dragonslotos.foundation.R.id.namePost)
        val body = holder.itemView.findViewById<TextView>(com.dragonslotos.foundation.R.id.bodyPost)
        val Card = holder.itemView.findViewById<MaterialCardView>(com.dragonslotos.foundation.R.id.card)
        Text.text = data[position].name // Получение человека из списка данных по позиции
        if(data[position].body.length >= 100){
            var mawa = data[position].body.dropLast(data[position].body.length - 100)
            body.text = mawa
        }
        else{
            body.text =data[position].body
        }


        Card.setOnClickListener {
            bundle.putString("PostName", data[position].name)
            bundle.putString("PostBody", data[position].body)
            bundle.putString("PostDate", data[position].date)
            bundle.putString("PostOwner", data[position].owner)
            fragmentJump()
        }
    }
    private fun fragmentJump() {
        var mFragment = com.dragonslotos.foundation.screens.post.Post(bundle)
        switchContent(R.id.nav_host_fragment_container, mFragment)
    }
    fun switchContent(id: Int, fragment: Fragment) {
        if (mContext == null) return
        if (mContext is MainActivity) {
            val mainActivity = mContext as MainActivity
            val frag: Fragment = fragment
            mainActivity.switchContent(id, frag)
        }
    }
    override fun getItemCount(): Int = data.size
}