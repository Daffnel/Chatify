package com.example.chattlyapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chattlyapp.ChattlyAppTheme
import com.example.chattlyapp.R
import com.example.chattlyapp.navigation.Routes
import com.example.chattlyapp.viewmodel.LoginScreenViewModel
import com.example.chattlyapp.viewmodel.LoginScreenViewModelFactory
import com.example.chattlyapp.navigation.NavigationHost


@Composable
fun LoginScreen(
    navController: NavController,
    factory: LoginScreenViewModelFactory,
    modifier: Modifier = Modifier,
    viewModel: LoginScreenViewModel = viewModel(factory = factory)
) {

    //logga ut ev. tidigare inlogg
    viewModel.loggOut()

    Log.d("!!!", viewModel.isUserLoggedIn().toString())

    var userName by viewModel.userName
    var password by viewModel.password




    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 42.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Spacer flytta ner allt i från toppen

        Spacer(modifier = Modifier.height(24.dp))


        Logotype()



        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()// Fyll hela skärmen
                    .padding(16.dp)
                    .fillMaxHeight(), // Lite marginal från kanterna
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //klickas det på banner ska man kunn registrera sig
                AddUserBanner() {
                    navController.navigate(Routes.UserProfileScreen.route + "/true")         // = true så får vi extra fält för att reg. ny användare
                }

                Spacer(Modifier.size(32.dp))

                Text(
                    text = "Logga in",
                    textAlign = TextAlign.Start,
                    fontSize = 45.sp,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Spacer(Modifier.size(16.dp))



                LoginNameField(userName, onUserNameChange = { viewModel.onUserNameChange(it) })

                Spacer(Modifier.size(24.dp))

                LoginPassowrd(password, onPasswordChange = { viewModel.onPasswordChange(it) })

                Spacer(Modifier.size(16.dp))

                RemberMeNewPassword()

                Spacer(Modifier.size(32.dp))

                val context = LocalContext.current

                ElevatedButton(
                    onClick = {
                        if(!viewModel.isUserLoggedIn()) {   //todo Urk!!
                            viewModel.login()
                            navController.navigate(Routes.HomeScreen.route)
                        }else{
                            navController.navigate(Routes.ContactsScreen.route)
                        }
                    },
                    Modifier.fillMaxWidth()
                )
                {
                    Text(text = "Logga in")
                }

            }

        }
    }

}


@Composable
fun RemberMeNewPassword(viewModel: LoginScreenViewModel = viewModel()) {


    // var checkedState by remember { mutableStateOf(true) }    Körs i viewmodel

    Row(
        modifier = Modifier, horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = viewModel.remainLoginIn,
            onCheckedChange = { viewModel.remainLoginIn = !viewModel.remainLoginIn })

        Text(text = "kom ihåg mig")

        Spacer(Modifier.size(8.dp))
        Text(
            text = " Glömt lösenordet",
            Modifier.clickable { /*TODO */ },
            textDecoration = TextDecoration.Underline,
            color = Color.Red
        )

    }


}

@Composable
fun Logotype(modifier: Modifier = Modifier) {


    Row {
        Text(
            text = "C",
            Modifier.alignByBaseline(),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontSize = 100.sp,
            letterSpacing = 4.sp
        )

        Text(
            text = "hatify..",
            Modifier.alignByBaseline(),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            letterSpacing = 4.sp,
            fontSize = 60.sp
        )
    }
}

@Composable
fun LoginPassowrd(
    password: String,
    viewModel: LoginScreenViewModel = viewModel(),
    onPasswordChange: (String) -> Unit
) {


    //byt ögonicon om lösenord ska visas eller inte
    val icon = if (viewModel.passwordVisbility) {
        painterResource(R.drawable.visbility)
    } else {
        painterResource(R.drawable.not_visibi)
    }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = "Lösenord") },
        textStyle = MaterialTheme.typography.bodyLarge,
        shape = RoundedCornerShape(30),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_lock), contentDescription =
                "Hänglås"
            )
        },
        placeholder = { Text(text = "Password") },
        trailingIcon = {                                //ögat iconen
            IconButton(onClick = {
                viewModel.passwordVisbility = !viewModel.passwordVisbility
            }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (viewModel.passwordVisbility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}


@Composable
fun LoginNameField(userName: String, onUserNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = userName,
        onValueChange = onUserNameChange,
        label = { Text(text = "Användarnamn") },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(30)

    )

}

@Composable
fun AddUserBanner(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
    {
        Text(
            text = "Inte en Chatify användare än?\nKlicka här för att skapa ett konto",
            Modifier
                .align(Alignment.Center)
                .clickable { onClick() },
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.bodyLarge
        )

    }

}


@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun LoginScreenPreview() {

    ChattlyAppTheme {
        // LoginScreen()
    }
}

