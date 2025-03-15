package com.example.chattlyapp.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chattlyapp.data.NavBarItems

@Composable
fun BottomBarNavigation(navController: NavController){

    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()              //Håller koll på hur vi har navigerat
        val currentRoute = backStackEntry?.destination?.route

       NavBarItems.BarItems.forEach{ navItem ->
           NavigationBarItem(
               selected = currentRoute == navItem.route,
               onClick = {
                   navController.navigate(navItem.route){
                       popUpTo(navController.graph.findStartDestination().id){
                           saveState = true
                       }
                   launchSingleTop = true
                       restoreState = true
                   }
               },
               icon = {
                   Icon(imageVector = navItem.Image, contentDescription = navItem.title)
               },
               label = {
                   Text(text = navItem.title)
               },
           )
       }

    }
}