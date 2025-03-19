package com.example.chattlyapp.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


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