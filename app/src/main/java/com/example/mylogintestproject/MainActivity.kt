package com.example.mylogintestproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.mylogintestproject.ui.theme.MyLoginTestProjectTheme

class MainActivity : ComponentActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContent {
            MyLoginTestProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenBuilder(viewModel)
                }
            }
        }
    }
}

@Composable
fun ScreenBuilder( viewModel: MainViewModel){
    var email by rememberSaveable{ mutableStateOf("")}
    var password by rememberSaveable { mutableStateOf("")}

    Scaffold(topBar = { LoginTestTopBar() }) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = email,
                label = { Text(text = "E-mail") },
                placeholder = { Text(text = "someone@something.com") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = {email = it},
                )
            TextField(
                value = password,
                label = { Text(text = "Password") },
                placeholder = { Text(text = "enter your password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {password = it}
            )
            Button(onClick = { viewModel.login(email, password) }) {
                Text(text = "Login")
            }
            Text(
                text = viewModel.loginResult
            )

        }
    }
}

@Composable
fun LoginTestTopBar(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Welcome to login tester"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyLoginTestProjectTheme {

    }
}