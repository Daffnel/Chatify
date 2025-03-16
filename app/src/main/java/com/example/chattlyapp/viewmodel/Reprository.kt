package com.example.chattlyapp.viewmodel

import android.util.Log
import com.example.chattlyapp.data.UserProfile
import com.google.firebase.firestore.FirebaseFirestore

class Reprository(private val firebasemanger: FirebaseManger) {

    private val db = FirebaseFirestore.getInstance()

    fun userLogin(userName: String, password: String): Boolean {
       return firebasemanger.loginUser(userName, password)
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

    //Sparar användarens profil
    fun saveUserProfile(userProfile: UserProfile) {
        db.collection("users")
            .document()
            .set(userProfile)
            .addOnSuccessListener {
                Log.d("!!!", "ny profil skapad")
            }
            .addOnFailureListener { e ->
                Log.e("!!!", "Skapandet abv profile misslyckades", e)
            }

        }

    //Hämta lista med användare

    fun fetchUsers(callback: (List<UserProfile>) -> Unit){

            db.collection("users").get()
                .addOnSuccessListener { result ->
                    val userlist = result.documents.mapNotNull { dokument ->
                        dokument.toObject(UserProfile::class.java)
                    }
                    callback(userlist)
                    Log.d("!!!","Hämtade av användarlistan ok")
                }
                .addOnFailureListener { e ->
                    callback(emptyList())
                    Log.d("!!!","Misslyckades med att hämta användarw")
                }
    }

    }