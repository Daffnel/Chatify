package com.example.chattlyapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseManger {


     private val auth = FirebaseAuth.getInstance()

    fun getUserID() = auth.currentUser?.uid


    //Logga in en användare
    fun loginUser(userName: String, password: String): Boolean{

        var status: Boolean = true

        auth.signInWithEmailAndPassword(userName,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                status = true
                Log.d("!!!", "${status} användare inloggad")
            }
        }.addOnFailureListener { e ->
                 Log.e("!!!","inlogging misslyckades",e)
        status = false
            Log.d("!!!","Kärd det här nere??")
        }
        Log.d("!!!","Firebasemanger $status")
        return status
    }

    fun addNewUser(userName: String, password: String){

        auth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d("!!!","Ny användare skapad")
            }else {
             Log.w("!!!","Misslyckades med att skapa ny användare", task.exception)
            }
        }
    }

    fun isUserLoggedIn(): Boolean{
        return FirebaseAuth.getInstance().currentUser != null
    }

    public fun logoutUser(){

        Firebase.auth.signOut()
    }


}