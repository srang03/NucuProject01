package com.fi.nucu.project1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
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
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null){
            if(result.contents == null){ //the result data is null or empty then
                Toast.makeText(this, "The data is empty", Toast.LENGTH_SHORT).show()
            } else {

                Customer.scanQRcode = result.contents.toString()
                supportFragment.scanResultTextView.setText(result.contents.toString())
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
            }

        } else{ // the camera will not close if the result is still null
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


}
