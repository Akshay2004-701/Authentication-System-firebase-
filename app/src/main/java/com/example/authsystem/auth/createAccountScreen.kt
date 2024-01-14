package com.example.authsystem.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authsystem.ui.theme.AuthSystemTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = viewModel(),
    onSignUp:()->Unit,
    navigateToLogin:()->Unit = {}
){


    val state = viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "New? .. create an account",
            color = Color.Magenta,
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(top = 32.dp)
            )

        Text(
            text = "already have an account? .. sign in",
            color = Color.Cyan,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.clickable {
                navigateToLogin()
            }
            )
        Spacer(modifier = Modifier.height(64.dp))

        OutlinedTextField(
            value = state.value.email,
            onValueChange ={viewModel.updateEmail(it)},
            label = { Text(text = "email")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )

        OutlinedTextField(
            value = state.value.password,
            onValueChange ={viewModel.updatePassword(it)},
            label = { Text(text = "password")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        OutlinedButton(onClick = {onSignUp()}) {
            Text(text = "Sign up")
        }

    }
}

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun CreateAccountPreview(){
    AuthSystemTheme {
//    SignUpScreen(onSignUp = {s1:String , s2:String->{}})
    }
}