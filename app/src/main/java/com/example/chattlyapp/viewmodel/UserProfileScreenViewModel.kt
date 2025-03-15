package com.example.chattlyapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import com.example.chattlyapp.data.UserProfile

class UserProfileScreenViewModel(private val repro: Reprository): ViewModel()
{
    var userProfile by mutableStateOf(UserProfile())
        private set

    var userName = mutableStateOf("")
    var password = mutableStateOf("")


    fun updateFirstName(addFirstName: String){
        userProfile = userProfile.copy(firstName = addFirstName)
    }

    fun updateLastName(addLastName: String){
        userProfile = userProfile.copy(lastName = addLastName)
    }

    fun updateEmail(addEmail: String){
        userProfile = userProfile.copy(eMail = addEmail)
    }

    fun updateNickName(addfNickNamee: String){
        userProfile = userProfile.copy(nickName = addfNickNamee)
    }

    // lägg till en ny användare

    fun addNewUser(){
        val userName = userName.value
        val password = password.value

        repro.addNewUser(userName,password)


    }


}