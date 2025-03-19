package com.example.chattlyapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chattlyapp.navigation.NavigationHost
import com.example.chattlyapp.navigation.Routes
import com.example.chattlyapp.navigation.BottomBarNavigation


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

    @Composable
    fun MainScreen(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                if (shouldShowBottomBar(navController)) {  // Göm BottomBar vid vissa skärmar
                    BottomBarNavigation(navController)
                }


            }
        ) { innerPadding ->
            Box(modifier = Modifier.fillMaxSize().
            padding(innerPadding)) {
                NavigationHost(navController)
            }
        }


    }
//ta bort  bottomnavbar från loginskärmen samt profile skärmen om man ska skapa en ny användare
    @Composable
    fun shouldShowBottomBar(navController: NavController): Boolean {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val backStackEntry = navController.currentBackStackEntryAsState().value

    // Hämtar argument ifrån backstack om regNewUser = true ska baren gömas
        val regNewUser = backStackEntry?.arguments?.getString("regNewUser")?.toBoolean() ?: false
    //Om vi är på På ProfileScreen och regNewUser = true göm BottomNavBar
    if(currentRoute?.startsWith(Routes.UserProfileScreen.route) == true && regNewUser) {
        return false
    }else {
    return currentRoute != Routes.LoginScreen.route && currentRoute != Routes.LoginScreen.route
        }
    }
}

