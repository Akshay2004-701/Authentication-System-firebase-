package com.example.authsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authsystem.auth.AuthViewModel
import com.example.authsystem.auth.LoginScreen
import com.example.authsystem.auth.SignUpScreen
import com.example.authsystem.ui.theme.AuthSystemTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class RegisterActivity: ComponentActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContent{
            AuthSystemTheme {
                val viewModel:AuthViewModel = viewModel()
                val state = viewModel.uiState.collectAsState()

                if (state.value.isRegisterScreen){
                    SignUpScreen(onSignUp = {
                        createAccount(state.value.email,state.value.password)
                        viewModel.updatePage(false)
                        },
                        navigateToLogin = {
                            viewModel.updatePage(false)
                        })
                }
                else{
                    LoginScreen {
                        signIn(state.value.email,state.value.password)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        //this method signs in the user if he has already signed in
        if (currentUser!=null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(email:String , password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){
                    Toast.makeText(
                        applicationContext,
                        "You are successfully registered , login to continue",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else{
                    Log.e("AccountError", it.exception.toString())
                    Toast.makeText(
                        applicationContext,
                        it.exception.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }

    private fun signIn(email: String,password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        applicationContext,
                        "Successfully signed in",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else{
                    Toast.makeText(
                        applicationContext,
                        "Could not sign in ... enter the right credentials",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}