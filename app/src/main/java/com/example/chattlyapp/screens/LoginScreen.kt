package com.example.chattlyapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.alignByBaseline
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
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
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chattlyapp.ChattlyAppTheme
import com.example.chattlyapp.R
import kotlin.math.round

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 42.dp, start = 8.dp, end = 8.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Spacer flytta ner allt i från toppeen

        Spacer(modifier = Modifier.height(24.dp))


        Logotype()

//        Spacer(Modifier.size(0.dp))


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
                AddUserBanner()

                Spacer(Modifier.size(32.dp))

                Text(
                    text = "Logga in",
                    textAlign = TextAlign.Start,
                    fontSize = 45.sp,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer)

                Spacer(Modifier.size(16.dp))



                LoginNameField()

                Spacer(Modifier.size(24.dp))

                LoginPassowrd()

                Spacer(Modifier.size(16.dp))

                RemberMeNewPassword()

                Spacer(Modifier.size(32.dp))

                ElevatedButton(
                    onClick = {/*TODO */},
                    Modifier.fillMaxWidth())
                {
                    Text(text = "Logga in")
                }

            }

        }
    }

}

@Composable
fun RemberMeNewPassword() {

    var checkedState by remember { mutableStateOf(true) }

    Row(modifier = Modifier, horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.CenterVertically) {
    Checkbox(checked = checkedState, onCheckedChange = { checkedState = !checkedState})

    Text(text = "kom ihåg mig")

    Spacer(Modifier.size(8.dp))
    Text(text =" Glömt lösenordet",
        Modifier.clickable { /*TODO */ },
        textDecoration = TextDecoration.Underline,
        color = Color.Red)

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
            letterSpacing = 4.sp)

        Text(
            text = "hatify..",
            Modifier.alignByBaseline(),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            letterSpacing = 4.sp,
            fontSize = 60.sp)
    }
}

@Composable
fun LoginPassowrd() {

    // Tilstånd variabler för användarnamn och lösenord
    var password by remember { mutableStateOf("") }
    var passwordSynligt by remember { mutableStateOf(false) }

    val icon = if (passwordSynligt) {
        painterResource(R.drawable.visbility)
    } else {
        painterResource(R.drawable.not_visibi)
    }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text = "Lösenord") },
        textStyle = MaterialTheme.typography.bodyLarge,
        shape = RoundedCornerShape(30),
        leadingIcon = {Icon(painter = painterResource(R.drawable.ic_lock), contentDescription =
        null)},
        placeholder = { Text(text = "Password") },
        trailingIcon = {                                //ögat iconen
            IconButton(onClick = {
                passwordSynligt = !passwordSynligt
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
        visualTransformation = if (passwordSynligt) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}


@Composable
fun LoginNameField() {

    var userName by remember { mutableStateOf("") } // Skapåa ett tillstånd för username

    OutlinedTextField(
        value = userName,
        onValueChange = { userName = it },
        label = { Text(text = "Användarnamn") },
        leadingIcon = {Icon(painter = painterResource(R.drawable.ic_user), contentDescription = null)},
        shape = RoundedCornerShape(30)

    )

}

@Composable
fun AddUserBanner() {

    Box(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant))
    {
        Text(
            text = "Inte en Chatify användare än?\nKlicka här för att skapa ett konto",
            Modifier
                .align(Alignment.Center)
                .clickable { },
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.bodyLarge
        )

    }
}



@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun LoginScreenPreview() {

    ChattlyAppTheme {
        LoginScreen()
    }
}

