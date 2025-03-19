package com.example.chattlyapp.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.chattlyapp.data.model.repository.Reprository

class UserProfileScreenViewModelFactory(private val repository: Reprository): NewInstanceFactory(){
    override fun<T : ViewModel> create(modelClass: Class<T>): T = UserProfileScreenViewModel(repository) as T

}