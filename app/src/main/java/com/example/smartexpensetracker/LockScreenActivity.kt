package com.example.smartexpensetracker

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.smartexpensetracker.navigation.LockNavigation
import com.example.smartexpensetracker.navigation.SignInNav
import com.example.smartexpensetracker.onBoarding.LoginScreen

class LockScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}
