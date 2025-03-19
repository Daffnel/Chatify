package com.example.chattlyapp.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.chattlyapp.data.model.repository.Reprository

class LoginScreenViewModelFactory(private val repository: Reprository): NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T = LoginScreenViewModel(repository) as T
}