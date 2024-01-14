package com.example.authsystem.auth

data class AuthState(
    val email:String = "",
    val password:String = "",
    val isRegisterScreen:Boolean = true
)
