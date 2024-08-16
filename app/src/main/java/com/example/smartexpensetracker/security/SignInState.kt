package com.example.smartexpensetracker.security

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
