package com.example.smartexpensetracker.security

data class SignInResult(
    val data: com.example.smartexpensetracker.security.UserData?,
    val errorMessage: String?
)
data class UserData(
    val id: String?,
    val name: String?,
    val profileUrl: String?
)