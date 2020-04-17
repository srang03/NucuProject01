package com.fi.nucu.project1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fi.nucu.project1.com.fi.nucu.project1.customer.Customer
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_support.view.*
import okhttp3.*
import java.io.IOException


class SupportFragment : Fragment() {

    private var count = 0
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

            val organization: String = view.organizationEditText.text.toString().replace(" ", "+")



            val unit: String = view.unitEditText.text.toString().replace(" ", "+")


            val additional: String = view.additionalEditText.text.toString().replace(" ", "+")


            val scanQRcode = Customer.scanQRcode


            if(scanQRcode.isEmpty()){
                Toast.makeText(activity, "You have to input QR code of product", Toast.LENGTH_LONG).show()

            }

                if(problem.isNotEmpty() && organization.isNotEmpty() && unit.isNotEmpty() && additional.isNotEmpty() && scanQRcode.isNotEmpty()){
                    val url = "https://docs.google.com/forms/d/e/1FAIpQLSfuEsjZUp2x-vlHxT3BVJLz1HYcp5MIVxvnz5pI4FMauIf70g/formResponse?usp=pp_url&entry.257058604=${scanQRcode}&entry.1171963606=${problem}&entry.1715107161=${organization}&entry.57378209=${unit}&entry.1949686626=${Customer.customerName}&entry.1925872678=${Customer.customerEmail}&entry.110348760=${Customer.customerphone}&entry.765507324=${additional}"

                    val request = Request.Builder().url(url).build()
                    val client = OkHttpClient()

                    client.newCall(request).enqueue(object: Callback {

                        override fun onFailure(call: Call, e: IOException) {
                            activity?.runOnUiThread {
                                Toast.makeText(activity, "Fail to connect Nucu service", Toast.LENGTH_SHORT).show()
                            }

                        }

                        override fun onResponse(call: Call, response: Response) {
                            activity?.runOnUiThread {
                                Toast.makeText(activity, "You have sent successfully!!", Toast.LENGTH_SHORT).show()
                            }


                        }


                    })


                }
            else{
                    if(additional.isEmpty()){
                        Toast.makeText(activity, "You have to input all clauses", Toast.LENGTH_LONG).show()

                    }
                }
            }




        return view
    }
        private fun initScan(){
            val scanner = IntentIntegrator(activity)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }

}
