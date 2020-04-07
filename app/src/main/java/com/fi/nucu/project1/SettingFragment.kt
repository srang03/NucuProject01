package com.fi.nucu.project1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fi.nucu.project1.com.fi.nucu.project1.customer.Customer
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view: View = inflater.inflate(R.layout.fragment_setting, container, false)

        Log.d("Tag", "${Customer.customerId}").toString()
        Log.d("Tag", "${Customer.customerName}")
        Log.d("Tag", "${Customer.address}")


        handler.post{
            idSetTextView.text = Customer.customerId.toString()
            nameSetTextView.setText(Customer.customerName)
            addressSetTextView.setText(Customer.address)
        }


        return view
    }


}


