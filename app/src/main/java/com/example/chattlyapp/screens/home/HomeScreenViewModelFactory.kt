package com.example.chattlyapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chattlyapp.data.model.repository.HomeScreenRepository
import com.example.chattlyapp.screens.chat.ChatScreenViewModel

class HomeScreenViewModelFactory (private val repository: HomeScreenRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T: ViewModel> create(modeClass: Class<T>): T = HomeScreenViewModel(repository) as T
}