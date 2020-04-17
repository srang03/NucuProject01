package com.fi.nucu.project1

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fi.nucu.project1.com.fi.nucu.project1.customer.Customer
import kotlinx.android.synthetic.main.fragment_feedback.view.*
import okhttp3.*
import java.io.IOException


class FeedbackFragment : Fragment() {

    lateinit var checkOption: String
    var otherCheck: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_feedback, container, false)

        fun otherClear(){
            view.otherEditText.text.clear()
            view.otherEditText.visibility = View.INVISIBLE
        }

        view.feedBackRadioButton.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.platformsRadioButton -> {
                        checkOption = "Nucu hyvinvointialustoja+/+Nucu platforms"
                        otherClear()
                        otherCheck = false
                    }

                    R.id.servicesRadioButton -> {
                        checkOption = "Palvelua+/+Services"
                        otherClear()
                        otherCheck = false
                    }
                    R.id.soundscapesRadioButton -> {
                        checkOption = "Äänimaailmoja+/+Soundscapes"
                        otherClear()
                        otherCheck = false
                    }
                    R.id.websiteRadioButton -> {
                        checkOption = "Nettisivuja+/+Website"
                        otherClear()
                        otherCheck = false
                    }
                    R.id.otherRadioButton -> {checkOption = "__other_option__&entry.1233643817.other_option_response="

                        view.otherEditText.visibility = View.VISIBLE
                        otherCheck = true

                    }

                }

                Log.d(TAG, checkOption)
            }

        })
        view.feedBackSendButton.setOnClickListener {
            if (view.feedBackRadioButton.checkedRadioButtonId == -1 || view.ideaEditText.text.isEmpty()){
                Toast.makeText(getActivity(), "You have to input All things", Toast.LENGTH_SHORT).show()
            }

            else {
                val idea = view.ideaEditText.text.toString().replace(" ", "+")
                if(otherCheck){
                    if(view.otherEditText.text.isEmpty()){
                        Toast.makeText(getActivity(), "You should input for other", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val url = "https://docs.google.com/forms/d/e/1FAIpQLSevfFjk8nPR6fFG5HtWBruReOu8BBvWfTkpn74ZcJNJ3pNBCQ/formResponse?usp=pp_url&entry.1830719459=${Customer.customerName}&entry.1621190537=${Customer.customerEmail}&entry.769296181=${Customer.customerphone}&entry.1233643817=${checkOption.plus(view.otherEditText.text.toString().replace(" ", "+"))}&entry.1737200863=${idea}"
                        Log.d(TAG, idea)
                        Log.d(TAG, checkOption)

                        Log.d(TAG, url)

                        val request = Request.Builder().url(url).build()
                        val client = OkHttpClient()
                        client.newCall(request).enqueue(object: Callback{
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
                }
                else {
                    val url = "https://docs.google.com/forms/d/e/1FAIpQLSevfFjk8nPR6fFG5HtWBruReOu8BBvWfTkpn74ZcJNJ3pNBCQ/formResponse?usp=pp_url&entry.1830719459=${Customer.customerName}&entry.1621190537=${Customer.customerEmail}&entry.769296181=${Customer.customerphone}&entry.1233643817=${checkOption.plus(view.otherEditText.text.toString().replace(" ", "+"))}&entry.1737200863=${idea}"


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


            }

        }

        return view
    }



}
