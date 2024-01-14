package com.example.authsystem.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel:ViewModel() {
    private var _uiState = MutableStateFlow(AuthState())
    val uiState = _uiState.asStateFlow()

    fun updateEmail(email:String){
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password:String){
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun updatePage(toLogin:Boolean){
        _uiState.update {
            it.copy(isRegisterScreen = toLogin)
        }
    }

}