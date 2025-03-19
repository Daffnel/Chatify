package com.example.chattlyapp.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chattlyapp.data.model.ChatData
import com.example.chattlyapp.navigation.Routes
import com.example.chattlyapp.screens.chat.ChatScreenViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homescreen(modifier : Modifier = Modifier.fillMaxSize(),
               viewModel: HomeScreenViewModel,
               navController: NavController){

    val recentChats by viewModel.latestMessages().collectAsState(initial = emptyList())


    val onListItemClick = { chatId: String ->

            Log.d("!!!", "Navigating to chat: ${chatId} with user: ${chatId}")

        navController.navigate("${Routes.ChatScreen.route}/$chatId/$chatId")

    }


    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp) ){
        items(recentChats) { chat ->
            messagesList(chat, onItemClick = {userId ->
                onListItemClick(userId)
            })

        }
    }


}



@Composable

//var chatId: String = ""

fun messagesList(messages: ChatData, onItemClick: (String)-> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(messages.chatId) },
            elevation = CardDefaults.cardElevation(4.dp)
    ){
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = messages.users[1])
            Spacer(modifier = Modifier.weight(1f))
            Text(text = messages.lastMessage, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(messages.timestamp)))

        }
    }

}






/*
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPrevie(){

    val navController = rememberNavController()
    Homescreen()
}*/
