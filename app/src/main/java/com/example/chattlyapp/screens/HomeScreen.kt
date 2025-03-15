package com.example.chattlyapp.screens

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.chattlyapp.MainActivity
import com.example.chattlyapp.navigation.Routes


@Composable
fun Homescreen(modifier: Modifier = Modifier.fillMaxSize()){

    val navController = rememberNavController()

Scaffold(topBar = { TopAppBar(title = {Text("Chatify")}) },
    content = {padding ->
        Column(Modifier.padding(padding)) {
            MainActivity.
        }
    })

    Text(text = "Home Screen")


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPrevie(){

    val navController = rememberNavController()
    Homescreen(navController = navController)
}