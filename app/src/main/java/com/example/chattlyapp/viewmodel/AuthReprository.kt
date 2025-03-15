package com.example.chattlyapp.viewmodel

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class AuthReprository(private val firebasemanger: FirebaseManger) {

fun userLogin(userName: String, password: String){

firebasemanger.loginUser(userName, password)

}





}