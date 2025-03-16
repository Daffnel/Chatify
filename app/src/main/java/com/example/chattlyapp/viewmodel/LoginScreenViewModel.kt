package com.example.chattlyapp.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.protobuf.Internal.BooleanList


class LoginScreenViewModel(private val repro: Reprository) : ViewModel(){

    var passwordVisbility by mutableStateOf(false)
    var remainLoginIn by mutableStateOf(true)


    var password = mutableStateOf("")
    var userName = mutableStateOf("")


    fun onUserNameChange(newUserName: String){
        userName.value = newUserName

    }

    fun onPasswordChange(newPassword: String){
        password.value = newPassword
    }

    fun isUserLoggedIn():Boolean{
        return  repro.isuserLoggedIn()
    }

    fun loggOut(){
        repro.loggOut()
    }

    fun login(){
        val userName = userName.value
        val password = password.value

         repro.userLogin(userName,password)
        }




    fun customToast(message: String, context: Context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}


