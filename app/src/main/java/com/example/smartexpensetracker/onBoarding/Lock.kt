package com.example.smartexpensetracker.onBoarding

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.smartexpensetracker.LockScreenActivity
import com.example.smartexpensetracker.securitya.BiometricPromptManager
import com.example.smartexpensetracker.securitya.BiometricPromptManager.BiometricResult


@Composable
fun Lock(activity: LockScreenActivity, navController: NavController) {
    val hasLaunched = remember { mutableStateOf(false) }

    val promptManager by lazy { BiometricPromptManager(activity) }
    val biometricResult by promptManager.promptResults.collectAsState(initial = null)
    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { println("Activity Result: $it") }
    )

    LaunchedEffect(biometricResult) {
        if (biometricResult is BiometricResult.AuthenticationNotSet) {
            if (Build.VERSION.SDK_INT >= 30) {
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                    )
                }
                enrollLauncher.launch(enrollIntent)
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!hasLaunched.value) {
            promptManager.showBiometricAuth(
                title = "Unlock Tracker",
                description = "Authenticate to open the Tracker"
            )
            hasLaunched.value = true
        }
        biometricResult?.let { result ->
            when (result) {
                is BiometricResult.AuthenticationSucceeded -> {
                    navController.navigate("SwipingAnimation")
                }
                is BiometricResult.AuthenticationError -> {
                    TODO()
                }
                BiometricResult.AuthenticationFailed -> TODO()
                BiometricResult.AuthenticationNotSet -> TODO()
                BiometricResult.FeatureUnavailable -> TODO()
                BiometricResult.HardwareUnavailable -> TODO()
            }
        }
    }
}
