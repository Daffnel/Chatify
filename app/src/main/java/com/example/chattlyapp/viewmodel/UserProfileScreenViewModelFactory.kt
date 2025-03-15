package com.example.chattlyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class UserProfileScreenViewModelFactory(private val repository: Reprository): NewInstanceFactory(){
    override fun<T : ViewModel> create(modelClass: Class<T>): T = UserProfileScreenViewModel(repository) as T

}