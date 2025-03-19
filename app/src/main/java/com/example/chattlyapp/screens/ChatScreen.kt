package com.example.chattlyapp.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chattlyapp.data.Messages
import com.example.chattlyapp.viewmodel.ChatRepository
import com.example.chattlyapp.viewmodel.ChatScreenViewModel

@Composable
fun ChatScreen(viewModel: ChatScreenViewModel,
               modifier: Modifier = Modifier,
               chatId: String,
               username: String,
               navController: NavController) {

   // Log.d("!!!","Chat id i chaten ${chatId}")

    var messageText by remember { mutableStateOf("") }
    val messagesTemp by viewModel.getMessages(chatId).collectAsState(initial = emptyList())



    Column(modifier = Modifier
        .padding(16.dp)) {

        Text(text = "Chat med $username",
            modifier = Modifier.padding(top = 32.dp),
            fontSize = 18.sp
            )
        HorizontalDivider(thickness = 3.dp, color = Color.Black)

        LazyColumn {
            items(messagesTemp) { medddelande ->
               ChatBubble(medddelande)
            }
        }
        Spacer(modifier = Modifier.weight(1F) )   //skicar ner mot botten
        HorizontalDivider(thickness = 2.dp, color = Color.Black)

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically){

            OutlinedTextField(value = messageText,
                onValueChange = { messageText = it },
                label = { Text("Skriv dit meddlande här") },
                modifier = Modifier
                    .padding(end = 8.dp))
            Button(onClick = {
                viewModel.senMessages(chatId, messageText)
                messageText = ""
            },
                modifier = Modifier.fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text= "Skicka!")
            }
        }
        }
}

@Composable
fun ChatBubble(message: Messages) {// isSender: Boolean) {

    //fixa så att bubblorna byter plats
    val personOne = message.senderId


    var isSender: Boolean = true

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (isSender) Arrangement.End else Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isSender) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier
                .widthIn(max = 250.dp)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = if (isSender) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}


