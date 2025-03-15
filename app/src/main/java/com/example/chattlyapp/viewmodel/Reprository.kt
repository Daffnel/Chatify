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

    fun saveUserProfile(userProfile: UserProfile){

        //val userId = firebasemanger.getUserID()

       // Log.d("!!!","anvädare = $userId")
        Log.d("!!!","$userProfile")


    }
}