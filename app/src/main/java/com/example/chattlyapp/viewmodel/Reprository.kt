package com.example.chattlyapp.viewmodel

import android.util.Log
import com.example.chattlyapp.data.UserProfile
import com.google.firebase.firestore.FirebaseFirestore

class Reprository(private val firebasemanger: FirebaseManger) {

    private val db = FirebaseFirestore.getInstance()

    fun userLogin(userName: String, password: String) {
       firebasemanger.loginUser(userName, password)
    }

    fun addNewUser(userName: String, password: String) {
        firebasemanger.addNewUser(userName, password)
    }

    fun getUserId(): String{
        return    firebasemanger.getUserID() ?: "inget id"
    }

    fun isuserLoggedIn(): Boolean {
         return firebasemanger.isUserLoggedIn()
    }

    fun loggOut(){
        firebasemanger.logoutUser()
    }
    fun saveUserProfile(userProfile: UserProfile){

        Log.d("!!!","$userProfile")


    }
}