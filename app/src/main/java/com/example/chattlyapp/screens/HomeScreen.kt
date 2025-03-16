package com.example.chattlyapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chattlyapp.navigation.NavigationHost


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homescreen(modifier: Modifier = Modifier.fillMaxSize()) {


}


   // Text(text = "Home Screen")




@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPrevie(){

    val navController = rememberNavController()
    Homescreen()
}