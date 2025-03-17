package com.example.chattlyapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chattlyapp.viewmodel.ChatScreenViewModel


/* val chatList = ChatMockUpData.dummyChat

    LazyColumn {
        items(chatList) { chat ->
           ChatListItem(chat)
        }
    }*/


@Composable
fun ChatScreen(viewModel: ChatScreenViewModel,
               modifier: Modifier = Modifier,
               userId: String,
               username: String,
               navController: NavController){



    val chatMessages = listOf(
        "Hej! Hur mår du?" to false,
        "Jag mår bra, tack! Du då?" to true,
        "Jag är också bra! Vad gör du?" to false,
        "Pluggar Jetpack Compose 😃" to true
    )

    Text("Chat med Username")

    LazyColumn {
        items(chatMessages) {(message, isSender)->
            ChatBubble(messages = message, isSender = isSender)

        }
    }

}

@Composable
fun SendMessage(modifier: Modifier = Modifier){




}

@Composable
fun ChatBubble(messages: String, isSender: Boolean) {
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
                text = messages,
                modifier = Modifier.padding(12.dp),
                color = if (isSender) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChatScreen()
{
    val fakeViewModel: ChatScreenViewModel = viewModel()
    ChatScreen(viewModel = fakeViewModel)
}*/
