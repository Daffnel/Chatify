package com.example.chattlyapp.screens.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chattlyapp.data.model.repository.ChatRepository

class ChatScreenViewModelFactory(private val repository: ChatRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modeClass: Class<T>): T = ChatScreenViewModel(repository) as T
}