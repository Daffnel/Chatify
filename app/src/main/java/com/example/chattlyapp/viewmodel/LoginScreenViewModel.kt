package com.example.chattlyapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginScreenViewModel: ViewModel() {

/*Private set kan endast läsas inom klassen */
    var userName by mutableStateOf("")

    var password by mutableStateOf("")

    var passwordVisbility by mutableStateOf(false)

    var remainLoginIn by mutableStateOf(true)



    //kontrolera om om Email är i rätt format


    fun customToast(message: String, context: Context){

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }

}