package com.example.chattlyapp.screens.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chattlyapp.data.model.repository.Reprository

class ContactsScreenViewModelFactory(private val reprository: Reprository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ContactsScreenViewModel(reprository) as T
}