package com.example.chattlyapp.screens.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chattlyapp.data.model.Messages
import com.example.chattlyapp.data.model.repository.ChatRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
class ChatScreenViewModel(private  val repository: ChatRepository): ViewModel() {


    fun getCurrentUser() {

     //  Log.d("!!!","Curren user= $currentUser")
        //val currentUser = auth.currentUser?.email ?: " "

       //return currentUser
    }


    fun StartChatWithUser(contactEmail: String) {
        repository.StartChatWithUser(contactEmail, { chatId -> Unit })
    }

    fun senMessages(chatID: String, text: String) {
        repository.sendMessage(chatID, text)
    }

    fun getMessages(chatID: String): Flow<List<Messages>> {


        return repository.getMessages(chatID)
    }

    fun setChatid(userEmail: String): String {

        return repository.setChatId(userEmail)
    }

}