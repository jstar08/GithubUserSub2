package com.example.githubusersub2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.githubusersub2.ui.home.MainActivity

class SplashScreen : AppCompatActivity() {
    private val TIMEOUT = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIMEOUT)
    }
}