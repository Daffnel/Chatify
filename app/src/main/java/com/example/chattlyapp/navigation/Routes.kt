package com.example.chattlyapp.navigation


//Navigationbsa rutter för de olika skärmarna
sealed class Routes(val route: String) {

    object ChatScreen: Routes("chatScreen")
    object ContactsScreen: Routes("contactsScreen")
    object LoginScreen: Routes("loginScreen")
    //object RegisterScreen: Routes("RegisterScreen")
   object UserProfileScreen: Routes("UserProfileScreen")


}