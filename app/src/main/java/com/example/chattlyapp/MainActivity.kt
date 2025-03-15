package com.example.chattlyapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chattlyapp.navigation.Routes
import com.example.chattlyapp.screens.ChatScreen
import com.example.chattlyapp.screens.ContactScreen
import com.example.chattlyapp.screens.LoginScreen
import com.example.chattlyapp.screens.RegistrerScreen
import com.example.chattlyapp.screens.UserProfileScreen
import com.example.chattlyapp.viewmodel.AuthReprository
import com.example.chattlyapp.viewmodel.FirebaseManger
import com.example.chattlyapp.viewmodel.LoginScreenViewModel
import com.example.chattlyapp.viewmodel.LoginScreenViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : ComponentActivity() {


    val CONTACTS_REQUEST_CODE = 101   //vilken vi använder senare för att identifera tillståndet

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Log.d("!!!", "Tillstånd har blivit nekat av användaren")
        } else {
            Log.d("!!!", "Tillstånd har blivit godkänt av användaren")
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        setupPremissions()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            ChattlyAppTheme {
                MainScreen()
            }
        }
    }
    //Ordna med tillstånd att få läsa telefonboken
    fun setupPremissions() {

        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("!!!", "Inget tillstånd att läsa kontakter")

            // Premission at runtime

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS), CONTACTS_REQUEST_CODE
            )

        }
    }

    @Composable             //Sköter navigationen
    fun MainScreen() {
        val navController = rememberNavController()

        //ok. vi får lura LoginScreen på en factory :-)
        val factory: LoginScreenViewModelFactory =
            LoginScreenViewModelFactory(AuthReprository(firebasemanger = FirebaseManger()))

        NavHost(
            navController = navController,
            startDestination = Routes.LoginScreen.route
        ) { //Todo ändra till rätt dest.

            composable(Routes.LoginScreen.route) {
                LoginScreen(factory, navController = navController)
            }

            composable(Routes.ChatScreen.route) {
                ChatScreen(navController = navController)
            }

            composable(Routes.ContactsScreen.route) {
                ContactScreen(navController = navController)
            }

            composable(Routes.UserProfileScreen.route) {
                UserProfileScreen(navController = navController)
            }


            // composable(Routes.RegisterScreen.route){
            //    RegistrerScreen(navController = navController)
        }
    }

}

