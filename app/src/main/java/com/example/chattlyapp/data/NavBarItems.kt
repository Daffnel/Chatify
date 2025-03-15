package com.example.chattlyapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings

object  NavBarItems {
    val BarItems = listOf(
        Baritem("Hem", Icons.Default.Home,"homescreen"),
        Baritem("Kontakter", Icons.Default.Person,"contactsScreen"),
        Baritem("Chat",Icons.Default.Call,"chatScreen"),
        Baritem("Profil",Icons.Default.Settings,"UserProfileScreen"),
        Baritem("LogOut", Icons.Default.Close,"loginscreen"))

}






/* Mina Navroutes
 object ChatScreen: Routes("chatScreen")
    object ContactsScreen: Routes("contactsScreen")
    object LoginScreen: Routes("loginScreen")
    //object RegisterScreen: Routes("RegisterScreen")
   object UserProfileScreen: Routes("UserProfileScreen")
    object HomeScreen: Routes("homeScreen")

 */