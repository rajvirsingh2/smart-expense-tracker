package com.example.smartexpensetracker.navigation

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartexpensetracker.security.SignInViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.smartexpensetracker.onBoarding.GoogleSignIn
import com.example.smartexpensetracker.security.GoogleAuthClient
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun SignInNav() {
    val context = LocalContext.current
    val googleAuthUiClient by lazy {
        GoogleAuthClient(
            context = context.applicationContext,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    val coroutineScope = rememberCoroutineScope() // Rename from lifecycleScope to avoid confusion
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "sign_in") {
        composable("sign_in") {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    coroutineScope.launch {
                        val signInResult = googleAuthUiClient.getSignInResult(
                            intent = result.data ?: return@launch
                        )
                        viewModel.onSignInResult(signInResult)
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "Sign In Success",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            GoogleSignIn(
                state = state,
                onSignInClick = {
                    coroutineScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }
    }
}