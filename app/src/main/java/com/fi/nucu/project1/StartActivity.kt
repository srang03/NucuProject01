package com.fi.nucu.project1

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.viewpager.widget.ViewPager
import com.fi.nucu.project1.com.fi.nucu.project1.customer.AppDatabase
import com.fi.nucu.project1.com.fi.nucu.project1.customer.Customer
import com.fi.nucu.project1.com.fi.nucu.project1.customer.Person
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.alert_popup.view.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class StartActivity : AppCompatActivity() {

    private val apiKey = "" // secret
    private val accessKey = "" // secret

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()


        if(db.personDao().getAll().isNotEmpty()) {
            Customer.customerId = db.personDao().getAll().get(0).customerId
            Customer.customerName = db.personDao().getAll().get(0).customerName.toString()
            Customer.address = db.personDao().getAll().get(0).address.toString()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }


        val pagerAdapter = PagerAdapter(supportFragmentManager)
        val pager: ViewPager = findViewById<ViewPager>(R.id.viewPager)
        pager.adapter = pagerAdapter

        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true








        customerSetButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                if(isConnected){
                    if (customerNumber.text.isBlank()) {
                        Toast.makeText(
                            this@StartActivity,
                            "Input your Customer Number Please!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if(customerNumber.text.toString().toIntOrNull() == null)
                            Toast.makeText(
                                this@StartActivity,
                                "Input customer number please!",
                                Toast.LENGTH_SHORT
                            ).show()
                        else{
                            if(customerNumber.text.toString().toInt() < 282 && customerNumber.text.toString().toInt() >= 1) {
                                var customerNumber = customerNumber.text.toString()


                                val url =
                                    "https://api.apptivo.com/app/dao/v6/customers?a=getAllBySearchText&startIndex=0&numRecords=1000&searchText={\"customerNumber\":\"${customerNumber}\"}&apiKey=${apiKey}&accessKey=${accessKey}"

                                val request = Request.Builder().url(url).build()

                                val client = OkHttpClient()
                                client.newCall(request).enqueue(object : Callback {
                                    override fun onFailure(call: Call, e: IOException) {
                                        Toast.makeText(this@StartActivity, "Fail for the online", Toast.LENGTH_LONG)
                                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                                    }

                                    override fun onResponse(call: Call, response: Response) {

                                        val body = response?.body?.string()

                                        val gson = GsonBuilder().create()
                                        val jObject = JSONObject(body)
                                        val jArray = jObject.getJSONArray("data")

                                        val obj = jArray.getJSONObject(0)
                                        Customer.customerId= obj.getInt("customerId")
                                        Customer.customerName = obj.getString("customerName")
                                        val address = obj.getJSONArray("addresses")

                                        val AObject = address.getJSONObject(0)
                                        val addressCity = AObject.getString("city")
                                        val addressLine1 = AObject.getString("addressLine1")

                                        val addressPost = AObject.getString("zipCode")
                                        Customer.address = "${addressPost}, ${addressLine1}, ${addressCity}"

                                        Log.d("Tag", "${Customer.customerId}")
                                        Log.d("Tag", "${Customer.customerName}")
                                        Log.d("Tag", "${Customer.address}")

                                        val handler = Handler(Looper.getMainLooper())

                                        val mDialogView = LayoutInflater.from(this@StartActivity).inflate(R.layout.alert_popup, null)
                                        val mBuilder = AlertDialog.Builder(this@StartActivity)
                                            .setView(mDialogView)
                                            .setTitle("Check your Customer Info")

                                        handler.post {
                                            val mAlertDialog = mBuilder.show()
                                            mDialogView.centerTextView.setText("Your name : ${Customer.customerName} \n Your Customer number : ${customerNumber}")
                                            mDialogView.saveButton.setOnClickListener {

                                                mAlertDialog.dismiss()

                                                db.personDao().insert(
                                                    Person(
                                                        Customer.customerId,
                                                        Customer.customerName,
                                                        Customer.address
                                                    )
                                                )


                                                val intent =
                                                    Intent(applicationContext, MainActivity::class.java)
                                                startActivity(intent)
                                            }




                                            mDialogView.cancelButton.setOnClickListener {
                                                mAlertDialog.dismiss()
                                            }

                                        }


                                    }


                                })


                            }
                            else {
                                Toast.makeText(this@StartActivity, "Check your Customer Number Please!", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                }
                else{
                    Toast.makeText(this@StartActivity, "You have to check your network service!", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }
}
