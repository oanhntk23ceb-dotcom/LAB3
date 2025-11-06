package com.example.lab8

sealed class Screen(val rout: String){
    object Home : Screen("home")
    object SignIn : Screen("signin")
    object SignUp : Screen("signup")
}