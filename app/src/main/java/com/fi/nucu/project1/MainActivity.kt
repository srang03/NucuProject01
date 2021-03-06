package com.fi.nucu.project1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.fi.nucu.project1.com.fi.nucu.project1.customer.Customer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_support.*

class MainActivity : AppCompatActivity() {


    lateinit var homeFragment: HomeFragment
    lateinit var feedbackFragment: FeedbackFragment
    lateinit var supportFragment: SupportFragment
    lateinit var settingFragment: SettingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG", "${Customer.customerId}")
        Log.d("TAG", "${Customer.customerName}")
        Log.d("TAG", "${Customer.customerEmail}")

        println("${Customer.customerId}")
        println("${Customer.customerName}")
        println("${Customer.customerEmail}")

        Toast.makeText(this,"Welcome to Nucu Service",Toast.LENGTH_LONG).show()

        val bottomNavigation: BottomNavigationView = btm_nav

        homeFragment = HomeFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()






        bottomNavigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.home -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.feedBack -> {
                    feedbackFragment = FeedbackFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, feedbackFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.support -> {
                    supportFragment = SupportFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, supportFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.setting -> {
                    settingFragment = SettingFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, settingFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                    Customer.scanQRcode = result.contents.toString()
                    scanResultTextView.setText(result.contents)

                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }


}
