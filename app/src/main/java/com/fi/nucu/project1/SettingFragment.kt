package com.fi.nucu.project1

import android.content.Intent
import android.net.Uri
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
import kotlinx.android.synthetic.main.fragment_setting.view.*

class SettingFragment : Fragment() {

    val handler = Handler(Looper.getMainLooper())
    val nucuNumber = "+3584578318730"
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


        view.phoneCall.setOnClickListener{

                startCall()
            }
        view.messageSend.setOnClickListener {
            sendMessage()
        }

        return view
    }

    private fun startCall(){
        val intent = Intent(Intent.ACTION_DIAL)

        intent.data =  Uri.parse("tel: " +nucuNumber)

        startActivity(intent)

    }

    private fun sendMessage(){
        val intent = Intent(Intent.ACTION_SEND)

        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf<String>("info@nucu.fi"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
        intent.putExtra(Intent.EXTRA_TEXT, "Input your question")

        startActivity(intent)
    }


}


