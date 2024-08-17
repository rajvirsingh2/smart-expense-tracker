package com.example.smartexpensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartexpensetracker.onBoarding.LoginScreen
import com.example.smartexpensetracker.onBoarding.SignUpScreen

@Composable
fun AccountNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") { LoginScreen(navController) }
        composable("SignUpScreen") { SignUpScreen(navController) }
    }
}
