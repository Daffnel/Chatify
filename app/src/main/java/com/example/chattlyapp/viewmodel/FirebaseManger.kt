package com.example.chattlyapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseManger {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()



    //Logga in en användare

    fun loginUser(userName: String, password: String){

        auth.signInWithEmailAndPassword(userName,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("!!!", "användare inloggad")
            }
        }.addOnFailureListener { e ->
                Log.e("!!!","inlogging misslyckades",e)

        }

    }

    fun logoutUser(){

        Firebase.auth.signOut()
    }


}