package com.example.chattlyapp.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegistrerScreen(navController: NavController){
    Text(text = "Registrer Screen")
}

@Preview(showBackground = true, showSystemUi = true )
@Composable
fun RegScreenPreview(){
    //dummy navcontroller
    val navcontroller = rememberNavController()
    RegisterScreen(navcontroller)
}