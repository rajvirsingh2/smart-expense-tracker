package com.example.smartexpensetracker.navigation

import SwipingAnimation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartexpensetracker.LockScreenActivity
import com.example.smartexpensetracker.onBoarding.Lock

@Composable
fun LockNavigation(activity: LockScreenActivity) {
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "LockScreen") {
            composable(route = "LockScreen") {
                Lock(activity = activity, navController = navController)
            }
            composable(route = "SwipingAnimation") {
                SwipingAnimation()
            }
        }
    }
}
