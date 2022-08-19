package com.example.mylogintestproject.network

data class LoginResponse(
    var id: Int,
    var title: String,
    var firstName : String,
    var lastName : String,
    var email: String,
    var userRole: String,
    var isVerified: Boolean,
    var jwtToken : String
)
