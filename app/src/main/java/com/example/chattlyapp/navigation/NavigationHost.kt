package com.example.chattlyapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chattlyapp.screens.ChatScreen
import com.example.chattlyapp.screens.ContactScreen
import com.example.chattlyapp.screens.Homescreen
import com.example.chattlyapp.screens.LoginScreen
import com.example.chattlyapp.screens.UserProfileScreen
import com.example.chattlyapp.viewmodel.FirebaseManger
import com.example.chattlyapp.viewmodel.LoginScreenViewModelFactory
import com.example.chattlyapp.viewmodel.Reprository
import com.example.chattlyapp.viewmodel.UserProfileScreenViewModelFactory

@Composable             //Sköter navigationen
fun NavigationHost(navController: NavHostController){

    val firebaseManager = remember { FirebaseManger() }

    val factoryLoginScreen = remember { LoginScreenViewModelFactory(Reprository(firebaseManager)) }
    val factoryUserProfileScreen = remember { UserProfileScreenViewModelFactory(Reprository(firebaseManager)) }



    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreen.route   //Todo ändra till rätt skärm
    ) {

        composable(Routes.LoginScreen.route) {
            LoginScreen(factory = factoryLoginScreen, navController = navController)
        }

        composable(Routes.ChatScreen.route) {
            ChatScreen()
        }

        composable(Routes.ContactsScreen.route) {
            ContactScreen()
        }

        composable(Routes.UserProfileScreen.route + "/{regNewUser}") { backStackEntry ->                  //Allt detta för att skicka med argument till UserProfileScreen för att inkludera fält för att reg ny användare
            val regNewUser = backStackEntry.arguments?.getString("regNewUser")?.toBoolean() ?: false

            UserProfileScreen(
                navController = navController,
                showRegScreen =  regNewUser,
                factory = factoryUserProfileScreen
            )
        }

        composable(Routes.HomeScreen.route) {
            Homescreen()
        }

        composable(Routes.LogOut.route){
            LoginScreen(factory = factoryLoginScreen, navController = navController)
        }
    }
}