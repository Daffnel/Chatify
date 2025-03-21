package com.example.chattlyapp.data.model.repository

import android.util.Log
import com.example.chattlyapp.data.model.UserInfoFromContacts
import com.example.chattlyapp.data.model.UserProfile
import com.example.chattlyapp.utils.FirebaseManger
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

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

        val userId = UUID.randomUUID().toString()    // Eftersom vi skapar profilen och user samtidt fr vi hitta på ett  eget userId+

        userProfile.userID = userId

        db.collection("users")
            .document(userId)
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
            Log.d("FireBase Data"," ${result}")
            result.documents.mapNotNull { it.toObject(UserInfoFromContacts::class.java)
            }
        }catch (e: Exception){
            emptyList()    //tom lista om det inte fungerar
        }

    }


    }