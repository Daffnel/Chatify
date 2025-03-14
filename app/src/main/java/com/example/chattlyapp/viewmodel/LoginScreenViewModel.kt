package com.example.chattlyapp.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class LoginScreenViewModel(repro: AuthReprository) : ViewModel(){

    var passwordVisbility by mutableStateOf(false)
    var remainLoginIn by mutableStateOf(true)

   /* private val _user = MutableStateFlow(authRepro.currentUser)
    val user: StateFlow<FirebaseUser?> = _user

    fun login(email: String, password: String){
        viewModelScope.launch {
            val loggedInUser = authRepro.login(email, password)
            _user.value = loggedInUser
        }
    }
//logga ut en användare
    fun logout() {
        authRepro.logOut()
        _user.value = null
    }

*/

    fun customToast(message: String, context: Context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}


