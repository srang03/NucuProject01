package com.fi.nucu.project1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {

    var handler: Handler? = null // Runnable을 실행하는 클래스
    var runnable: Runnable? = null // 병렬 실행이 가능한 Thread를 만들어주는 클래스

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun onResume() {
        super.onResume()

        runnable = Runnable {
            val intent = Intent(applicationContext, StartActivity::class.java)
            startActivity(intent)
        }

        handler = Handler()
        handler?.run {
            postDelayed(runnable, 4000)
        } // Handler를 생성하고 2000ms 후 runnable을 실행
    }

    override fun onPause() {
        super.onPause()

        handler?.removeCallbacks(runnable) // Activity Pause 상태일 경우 runnable도 중단
    }
}
