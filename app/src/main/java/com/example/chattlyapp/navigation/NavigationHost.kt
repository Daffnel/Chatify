package com.example.chattlyapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.chattlyapp.screens.chat.ChatScreen

import com.example.chattlyapp.screens.contacts.ContactScreen
import com.example.chattlyapp.screens.home.Homescreen
import com.example.chattlyapp.screens.login.LoginScreen
import com.example.chattlyapp.screens.profile.UserProfileScreen
import com.example.chattlyapp.data.model.repository.ChatRepository
import com.example.chattlyapp.data.model.repository.HomeScreenRepository
import com.example.chattlyapp.screens.chat.ChatScreenViewModel
import com.example.chattlyapp.screens.chat.ChatScreenViewModelFactory
import com.example.chattlyapp.screens.contacts.ContactsScreenViewModel
import com.example.chattlyapp.screens.contacts.ContactsScreenViewModelFactory
import com.example.chattlyapp.utils.FirebaseManger
import com.example.chattlyapp.screens.login.LoginScreenViewModelFactory
import com.example.chattlyapp.data.model.repository.Reprository
import com.example.chattlyapp.screens.home.HomeScreenViewModel
import com.example.chattlyapp.screens.home.HomeScreenViewModelFactory
import com.example.chattlyapp.screens.profile.UserProfileScreenViewModelFactory


@Composable             //Sköter navigationen
fun NavigationHost(navController: NavHostController){

    val firebaseManager = remember { FirebaseManger() }

    val factoryLoginScreen = remember { LoginScreenViewModelFactory(Reprository(firebaseManager)) }
    val factoryUserProfileScreen = remember { UserProfileScreenViewModelFactory(Reprository(firebaseManager)) }
    val factoryContactsScreen = remember { ContactsScreenViewModelFactory(Reprository(firebaseManager)) }
    val factoryChatScreen = remember { ChatScreenViewModelFactory(ChatRepository()) }
    val factorHomeScreen = remember { HomeScreenViewModelFactory(HomeScreenRepository()) }

    val contactsViewModel: ContactsScreenViewModel = viewModel(factory = factoryContactsScreen) //bäst att skicka viewmodels redan här för att undvika upprepning
    val chatScreenViewModel: ChatScreenViewModel = viewModel(factory = factoryChatScreen)
    val HomeScreenViewModel: HomeScreenViewModel = viewModel(factory = factorHomeScreen)

    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreen.route
    ) {

        composable(Routes.LoginScreen.route) {
            LoginScreen(factory = factoryLoginScreen, navController = navController)
        }

        composable(Routes.ChatScreen.route + "/{chatId}" + "/{showName}"){ backStackEntry ->
            val userId = backStackEntry.arguments?.getString("chatId") ?: "Inget Id "
            val showName = backStackEntry.arguments?.getString("showName") ?: "Inget användarenamn"

              ChatScreen(
                  viewModel = chatScreenViewModel,
                  chatId = userId,
                 username = showName
              )
          }

        composable(Routes.ContactsScreen.route) {
            ContactScreen(viewModel = contactsViewModel,
                        navController= navController,
                        chatScreenViewmodel = chatScreenViewModel)
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
            Homescreen(viewModel = HomeScreenViewModel,
                navController = navController,
                chatScreenViewModel = chatScreenViewModel)
        }

        composable(Routes.LogOut.route){
            LoginScreen(factory = factoryLoginScreen, navController = navController)
        }
    }
}