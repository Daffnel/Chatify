package com.example.chattlyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class LoginScreenViewModelFactory(private val repository: AuthReprository): NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T = LoginScreenViewModel(repository) as T
}