package com.example.chattlyapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarNavigation(navController: NavController) {



    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()              //Håller koll på hur vi har navigerat
        val currentRoute = backStackEntry?.destination?.route

      NavigationBarItem(
          selected = currentRoute == Routes.HomeScreen.route,
          onClick = { navController.navigate(Routes.HomeScreen.route)},
          icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "home") },
          label = {
              Text("Hem")
          })

        NavigationBarItem(
            selected = currentRoute == Routes.ContactsScreen.route,
            onClick = { navController.navigate(Routes.ContactsScreen.route)},
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "home") },
            label = {
                Text("Kontakter")
            })
        /*NavigationBarItem(
            selected = currentRoute == Routes.ChatScreen.route,
            onClick = { navController.navigate(Routes.ChatScreen.route + "/Inget ID" + "/vad som helst")},
            icon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "home") },
            label = {
                Text("Chat")
            })*/
        NavigationBarItem(
            selected = currentRoute == Routes.UserProfileScreen.route,
            onClick = { navController.navigate(Routes.UserProfileScreen.route + "/false")},  //false tar bort new user fälten
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "home") },
            label = {
                Text("Profile")
            })
        NavigationBarItem(
            selected = currentRoute == Routes.LogOut.route,
            onClick = {
                navController.navigate(Routes.LoginScreen.route)},
            icon = { Icon(imageVector = Icons.Default.Close, contentDescription = "Logga ut") },
            label = {
                Text("Logga ut")
            })


    }
}

