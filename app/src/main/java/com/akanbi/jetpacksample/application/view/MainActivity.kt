package com.akanbi.jetpacksample.application.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.akanbi.jetpacksample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startHomeScreen()
    }

    private fun startHomeScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, UserListActivity::class.java))
            finish()
        }, 2000)
    }
}
