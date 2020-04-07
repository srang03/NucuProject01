package com.fi.nucu.project1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fi.nucu.project1.com.fi.nucu.project1.customer.Customer
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_support.*
import kotlinx.android.synthetic.main.fragment_support.view.*
import okhttp3.*
import java.io.IOException


class SupportFragment : Fragment() {

    val handler = Handler(Looper.getMainLooper())
    lateinit var customerName: String
    lateinit var customerEmail: String
    lateinit var customerphone:String


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

            if(result != null){
                Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(activity,"Scanned", Toast.LENGTH_LONG).show()
                scanResultTextView.setText(result!!.contents)
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_support, container, false)

        handler.post{
            customerName = Customer.customerName
            customerEmail = Customer.customerEmail
            customerphone = Customer.customerphone
        }

        view.scanButton.setOnClickListener {
            initScan()
        }

        view.supportSendButton.setOnClickListener {
            val problem: String = view.problemEditText.text.toString().replace(" ", "+")

            val organization: String = view.organizationEditText.text.toString().replace(" ", "+")

            val unit: String = view.unitEditText.text.toString().replace(" ", "+")

            val additional: String = view.additionalEditText.text.toString().replace(" ", "+")

            val scanQRcode = Customer.scanQRcode



            val url = "https://docs.google.com/forms/d/e/1FAIpQLSfuEsjZUp2x-vlHxT3BVJLz1HYcp5MIVxvnz5pI4FMauIf70g/formResponse?usp=pp_url&entry.257058604=${scanQRcode}&entry.1171963606=${problem}&entry.1715107161=${organization}&entry.57378209=${unit}&entry.1949686626=${customerName}&entry.1925872678=${customerEmail}&entry.110348760=${customerphone}&entry.765507324=${additional}"

            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            Toast.makeText(getActivity(), "You have sent successfully!!", Toast.LENGTH_SHORT).show()
            client.newCall(request).enqueue(object: Callback {

                override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call, response: Response) {


                }


            })


        }


        return view
    }
        private fun initScan(){
            val scanner = IntentIntegrator(getActivity())
            scanner.initiateScan()
        }

}
