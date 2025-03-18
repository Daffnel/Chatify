package com.example.chattlyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChatScreenViewModelFactory(private val repository: ChatRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modeClass: Class<T>): T = ChatScreenViewModel(repository) as T
}