package com.dragonslotos.foundation.screens.table

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.screens.posts.Posts
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class Table(bundle: Bundle) : Fragment() {
    private var ctx:Context ?= null
    var bundle1 = bundle;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }
    init {
        Table.bundles = bundle1;
    }
    fun GetBundle(): Bundle {
        return bundle1;
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_table, container, false)
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = TableAdapter(ctx as FragmentActivity)
        val bnv = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bnv.setOnItemSelectedListener{ item ->
            when(item.itemId) {
                R.id.item_1 -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.item_2 -> {
                    // Respond to navigation item 2 click
                    viewPager.currentItem = 1
                    true
                }
                else -> false
            }
        }
        return view
    }


    companion object{
        @JvmStatic
        fun newInstance(bundle: Bundle) = Table(bundle)

        var bundles: Bundle = Bundle.EMPTY;
    }
}