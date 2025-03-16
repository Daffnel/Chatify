package com.example.chattlyapp.viewmodel

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import com.example.chattlyapp.data.UserInfoFromContacts
import com.example.chattlyapp.data.UserProfile
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

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
    suspend fun fetchUser(): List<UserInfoFromContacts>{
        return try {
            val result = db.collection("users").get().await()
            result.documents.mapNotNull { it.toObject(UserInfoFromContacts::class.java) }
        }catch (e: Exception){
            emptyList()    //tom lista om det inte fungerar
        }
    }


    }