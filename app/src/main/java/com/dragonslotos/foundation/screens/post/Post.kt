package com.dragonslotos.foundation.screens.post

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.dragonslotos.foundation.MainActivity
import com.dragonslotos.foundation.R
import com.dragonslotos.foundation.screens.table.Table

class  Post(bundle: Bundle) : Fragment() {

    val bundle1 = bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        val body: TextView? = view?.findViewById<TextView>(R.id.body)
        val name: TextView? = view?.findViewById<TextView>(R.id.postName)
        val time: TextView? = view?.findViewById<TextView>(R.id.Date)
        val owners: TextView? = view?.findViewById<TextView>(R.id.Owner)
        val returnButton: Button? = view?.findViewById<Button>(R.id.returnButton)

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