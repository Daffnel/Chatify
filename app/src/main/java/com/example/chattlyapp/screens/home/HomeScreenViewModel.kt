package com.example.chattlyapp.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chattlyapp.data.model.ChatData
import com.example.chattlyapp.data.model.Messages
import com.example.chattlyapp.data.model.repository.ChatRepository
import com.example.chattlyapp.data.model.repository.HomeScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: HomeScreenRepository): ViewModel() {



fun resumeChat(chatId: String){
    repository.resumeChat(chatId, onResult = {})
}

 fun latestMessages():Flow<List<ChatData>> {

        return  repository.getLatestMessages()
    }

}
