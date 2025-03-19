package com.example.chattlyapp.screens.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chattlyapp.data.model.UserInfoFromContacts
import com.example.chattlyapp.navigation.Routes
import com.example.chattlyapp.screens.chat.ChatScreenViewModel

@Composable
fun ContactScreen(navController: NavController,
                  viewModel: ContactsScreenViewModel,
                  chatScreenViewmodel: ChatScreenViewModel
){


   Column(modifier = Modifier
       .padding(top = 36.dp)) {

       HeaderText()
       ContactList(navController = navController,viewModel = viewModel, chatScreenViewmodel = chatScreenViewmodel)
   }


}

@Composable
fun HeaderText(modifier: Modifier = Modifier) {

    Text(text = "Vilka av dina kontakter använder Chatify",
        modifier= Modifier.padding(16.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSecondaryContainer)
}


@Composable
fun ContactList(navController: NavController, modifier: Modifier = Modifier,
                viewModel: ContactsScreenViewModel,
                chatScreenViewmodel: ChatScreenViewModel
){

    val context = LocalContext.current
    val contactsList = viewModel.getContact(context)

    LazyColumn(modifier) {
        items(contactsList) { contact ->
            ContactsListCard(navController = navController, contacts = contact, chatScreenViewmodel = chatScreenViewmodel)
        }
    }


}


@Composable
fun ContactsListCard(navController: NavController,
                     chatScreenViewmodel: ChatScreenViewModel,
                     modifier: Modifier = Modifier,
                     contacts: UserInfoFromContacts
){

    var showName = contacts.nickName.ifEmpty { contacts.firstName }



    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFCCE3F5)),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                if(contacts.isUser){
                    chatScreenViewmodel.StartChatWithUser(contacts.email)
                    val chatId = chatScreenViewmodel.setChatid(contacts.email)
                    navController.navigate(Routes.ChatScreen.route + "/$chatId" + "/$showName")
                }
                /* Gör inget personen är inte användare */
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)){
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0XFF1565C0),
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = contacts.firstName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = contacts.lastName, fontSize = 15.sp)
                if(contacts.isUser){
                    Text(text = "Jag använder chatify!!\n Här kan du kalla mig för ${contacts.nickName}",
                        color = Color(0xD5422D03), fontSize = 13.sp,
                        fontStyle = FontStyle.Italic)

                }
            }

        }
    }

}


/*
@Preview
@Composable
fun Preview(){

    //Falsk navController
    val navController = rememberNavController()
   // ContactsListCard()
    ContactScreen(navController = navController)

}*/
