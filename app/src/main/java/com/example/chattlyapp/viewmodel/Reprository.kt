package com.example.chattlyapp.viewmodel

class Reprository(private val firebasemanger: FirebaseManger) {

    fun userLogin(userName: String, password: String) {
        firebasemanger.loginUser(userName, password)
    }

    fun addNewUser(userName: String, password: String) {

        firebasemanger.addNewUser(userName, password)
    }


}