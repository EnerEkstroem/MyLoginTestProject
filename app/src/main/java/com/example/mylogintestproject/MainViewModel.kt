package com.example.mylogintestproject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylogintestproject.network.LoginApi
import com.example.mylogintestproject.network.LoginRequest
import com.example.mylogintestproject.network.LoginResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse : LiveData<LoginResponse> = _loginResponse

    var loginResult by mutableStateOf("")

    fun login(email: String, password: String){
        viewModelScope.launch {
            try {
                _loginResponse.value = LoginApi.retrofitService.login(LoginRequest(email, password))
                updateLoginResult("Welcome ${_loginResponse.value?.title} ${_loginResponse.value?.lastName}")
            } catch (e : Exception){
                updateLoginResult( "Login Failed : ${e.message}")
            }
        }
    }

    fun updateLoginResult(result: String){
        loginResult = result
    }
}