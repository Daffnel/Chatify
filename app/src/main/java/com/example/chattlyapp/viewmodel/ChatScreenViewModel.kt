package com.example.chattlyapp.viewmodel

import androidx.lifecycle.ViewModel

class ChatScreenViewModel(private  val repository: ChatRepository): ViewModel() {


    fun StartChatWithUser(contactEmail: String){
        repository.StartChatWithUser(contactEmail,{chatId -> Unit})
    }

}