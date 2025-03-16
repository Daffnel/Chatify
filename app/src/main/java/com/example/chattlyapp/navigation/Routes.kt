package com.example.chattlyapp.navigation


//Navigationbsa rutter för de olika skärmarna
sealed class Routes(val route: String) {

    object ChatScreen: Routes("chatScreen")
    object ContactsScreen: Routes("contactsScreen")
    object LoginScreen: Routes("loginScreen")
   object UserProfileScreen: Routes("UserProfileScreen")
    object HomeScreen: Routes("homeScreen")
    object LogOut: Routes("loginScreen")   //när man loggar ut kommer man till login skärmen


}