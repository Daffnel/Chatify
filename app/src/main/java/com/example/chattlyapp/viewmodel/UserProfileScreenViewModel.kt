package com.example.chattlyapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import com.example.chattlyapp.data.UserProfile

class UserProfileScreenViewModel: ViewModel()
{
    var userProfile by mutableStateOf(UserProfile())
        private set   //ändas ändras av view modeln


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



}