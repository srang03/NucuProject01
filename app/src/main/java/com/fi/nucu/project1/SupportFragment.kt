package com.fi.nucu.project1

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_support.view.*
import okhttp3.*
import java.io.IOException


class SupportFragment : Fragment() {
    val mainActivity = MainActivity()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_support, container, false)

        view.scanButton.setOnClickListener {
            initScan()
        }

        view.supportSendButton.setOnClickListener {
            val problem: String = view.problemEditText.text.toString().replace(" ", "+")
            Log.d(TAG, problem)
            val organization: String = view.organizationEditText.text.toString().replace(" ", "+")
            Log.d(TAG, organization)
            val unit: String = view.unitEditText.text.toString().replace(" ", "+")
            Log.d(TAG, unit)
            val additional: String = view.additionalEditText.text.toString().replace(" ", "+")
            Log.d(TAG, additional)
            val scanQRcode = mainActivity.Customer.scanQRcode
            Log.d(TAG, scanQRcode)


            val url = "https://docs.google.com/forms/d/e/1FAIpQLSfuEsjZUp2x-vlHxT3BVJLz1HYcp5MIVxvnz5pI4FMauIf70g/formResponse?usp=pp_url&entry.257058604=${scanQRcode}&entry.1171963606=${problem}&entry.1715107161=${organization}&entry.57378209=${unit}&entry.1949686626=${mainActivity.Customer.customerName}&entry.1925872678=${mainActivity.Customer.customerEmail}&entry.110348760=${mainActivity.Customer.customerphone}&entry.765507324=${additional}"
            Log.d(TAG, url)
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
