package com.example.chattlyapp.screens.home
import androidx.lifecycle.ViewModel
import com.example.chattlyapp.data.model.ChatData
import com.example.chattlyapp.data.model.repository.HomeScreenRepository
import kotlinx.coroutines.flow.Flow
class HomeScreenViewModel(private val repository: HomeScreenRepository): ViewModel() {



fun resumeChat(chatId: String){
    repository.resumeChat(chatId, onResult = {})
}

 fun latestMessages():Flow<List<ChatData>> {

        return  repository.getLatestMessages()
    }

}
