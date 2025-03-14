package com.example.chattlyapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.util.TableInfo
import com.example.chattlyapp.R
import com.example.chattlyapp.data.Contacts
import com.example.chattlyapp.viewmodel.ContactsScreenViewModel

@Composable
fun ContactScreen(modifier: Modifier = Modifier,navController: NavController){


   Column(modifier = Modifier
       .padding(top = 36.dp)) {


       HeaderText()

       ContactList()

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
fun ContactList(modifier: Modifier = Modifier, viewModel: ContactsScreenViewModel = viewModel()){


    val context = LocalContext.current

    val items = viewModel.getContact(context)

    LazyColumn(modifier) {
        items(items) { contact ->
            ContactsListCard(contacts = contact)
        }
    }

}


@Composable
fun ContactsListCard(contacts: Contacts){

    Card(colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp))
    {
        Row() {
            Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = contacts.firstName,
                        fontWeight = FontWeight.Bold
                    )

                    Text(text = contacts.eMail)
                }

            }
            Icon(
                painter = painterResource(android.R.drawable.btn_star_big_on),
                contentDescription = null,
            )
        }

}


@Preview
@Composable
fun Preview(){

    //Falsk navController
    val navController = rememberNavController()
   // ContactsListCard()
    ContactScreen(navController = navController)

}