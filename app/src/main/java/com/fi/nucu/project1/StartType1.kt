package com.fi.nucu.project1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class StartType1: Fragment() {
    fun newInstance(): StartType1{
        val args = Bundle()

        val frag = StartType1()
        frag.arguments = args

        return frag
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.type_1, container,false)
        return v
    }
}