package com.fi.nucu.project1

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting.view.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SettingFragment : Fragment() {
    private val apiKey = "imHxYZFZKvzt-PwUwPVBuMWEuUqYr-a3bea8a1-ec9f-4f64-8b26-dbd66a8a2d21"
    private val accessKey ="9tXt9OT3Qg0X6im7"
    var customerNumber: Int? = 0
    val mainActivity = MainActivity()
    override fun onPause() {

        customerVisibility.visibility = View.VISIBLE
        super.onPause()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_setting, container, false)

        if(mainActivity.Customer.customerId == -1){
            view.inputButton.setOnClickListener {
                if(inputEditText.text.isBlank()){
                    Log.d(TAG, "1111")
                    Toast.makeText(getActivity(),"Input your Customer Number Please!", Toast.LENGTH_SHORT).show()
                }
                else{
                    if(inputEditText.text.toString().toInt() > 200){
                        Toast.makeText(getActivity(),"Text!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        customerNumber = view.inputEditText.text.toString().toInt()
                        fetchJson()
                        view.customerVisibility.visibility = View.VISIBLE

                    }
                }
            }
        }
        else{
            view.idSetTextView.setText(mainActivity.Customer.customerId.toString())
            view.nameSetTextView.setText(mainActivity.Customer.customerName)
            view.addressSetTextView.setText(mainActivity.Customer.address)
            view.customerVisibility.visibility = View.VISIBLE
        }

        //

        return view
    }

    fun fetchJson() {


        val url = "https://api.apptivo.com/app/dao/v6/customers?a=getAllBySearchText&startIndex=0&numRecords=1000&searchText={\"customerNumber\":\"${customerNumber}\"}&apiKey=${apiKey}&accessKey=${accessKey}"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()

                val gson = GsonBuilder().create()
                val jObject = JSONObject(body)
                val jArray = jObject.getJSONArray("data")


                    val obj = jArray.getJSONObject(0)
                    val customerId: Int = obj.getInt("customerId")
                    val customerName: String = obj.getString("customerName")
                    val address = obj.getJSONArray("addresses")

                    val AObject = address.getJSONObject(0)
                    val addressCity = AObject.getString("city")
                    val addressLine1 = AObject.getString("addressLine1")
                    val addressZipCode = AObject.getString("zipCode")

                    Log.d(TAG, "${addressCity}")
                    Log.d(TAG, "${addressLine1}")
                    Log.d(TAG, "${addressZipCode}")
                mainActivity.Customer.setPerson(customerId, customerName, addressZipCode)

                idSetTextView.setText(mainActivity.Customer.customerId.toString())
                nameSetTextView.setText(mainActivity.Customer.customerName)
                addressSetTextView.setText(mainActivity.Customer.address)




            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }


        })
    }



}

