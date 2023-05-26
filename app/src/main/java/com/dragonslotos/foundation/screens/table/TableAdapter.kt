package com.dragonslotos.foundation.screens.table

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dragonslotos.foundation.screens.posts.Posts
import com.dragonslotos.foundation.screens.profile.Profile

class TableAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> Posts()
            else -> Profile()
        }
    }
}