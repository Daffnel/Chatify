package com.example.chattlyapp.screens.chat

import androidx.lifecycle.ViewModel
import com.example.chattlyapp.data.model.Messages
import com.example.chattlyapp.data.model.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatScreenViewModel(private  val repository: ChatRepository): ViewModel() {

    private val _messages = MutableStateFlow<List<Messages>>(emptyList())
    val messages: StateFlow<List<Messages>> = _messages

    fun StartChatWithUser(contactEmail: String) {
        repository.StartChatWithUser(contactEmail, { chatId -> Unit })
    }

    fun senMessages(chatID: String, text: String) {
        repository.sendMessage(chatID, text)
    }

    fun getMessages(chatID: String): Flow<List<Messages>> {

        var test = repository.getMessages(chatID)

        return repository.getMessages(chatID)
    }

    fun setChatid(userEmail: String): String {

        return repository.setChatId(userEmail)
    }

}