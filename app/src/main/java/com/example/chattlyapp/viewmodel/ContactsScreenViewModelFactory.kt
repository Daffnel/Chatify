package com.example.chattlyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContactsScreenViewModelFactory(private val reprository: Reprository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ContactsScreenViewModel(reprository) as T
}