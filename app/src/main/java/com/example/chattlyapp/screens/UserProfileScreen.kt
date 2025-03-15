package com.example.chattlyapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chattlyapp.ChattlyAppTheme
import com.example.chattlyapp.navigation.Routes
import com.example.chattlyapp.viewmodel.FirebaseManger
import com.example.chattlyapp.viewmodel.Reprository
import com.example.chattlyapp.viewmodel.UserProfileScreenViewModel
import com.example.chattlyapp.viewmodel.UserProfileScreenViewModelFactory

@Composable
fun UserProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    factory: UserProfileScreenViewModelFactory,
    viewModel: UserProfileScreenViewModel = viewModel(factory = factory),
    showRegScreen: Boolean = false) {

    var userName by viewModel.userName
    var password by viewModel.password

    Column(
        modifier = Modifier
            .padding(top = 42.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Surface(modifier = modifier.fillMaxWidth().
        padding(top = 24.dp, bottom = 16.dp),
            color = MaterialTheme.colorScheme.surface) {
            if (showRegScreen) {                 // Sätt titel beroende på om det är ny användare som ska registeras eller den befintliga profilen som ska visas
                Text("Skapa ny användare",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
            }else {
                Text(
                    "Användarprofil",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
            }
        }
        CustomInput(label = "Förnamn",
            value= viewModel.userProfile.firstName,
            icon = Icons.Default.Person,
            onValueChange = {viewModel.updateFirstName(it)})

        CustomInput(label = "Efternamn",
            value = viewModel.userProfile.lastName,
            icon = Icons.Default.Person,
            onValueChange = {viewModel.updateLastName(it)})

        CustomInput(label = "Nickname",
            value = viewModel.userProfile.nickName,
            icon = Icons.Default.Favorite,
            onValueChange = {viewModel.updateNickName(it)})

      // ta bort denna  input om det är en användare som ska reg. sig på nytt
       if(!showRegScreen) {
           CustomInput(label = "E-Post",
               value = viewModel.userProfile.eMail,
               icon = Icons.Default.Email,
               onValueChange = { viewModel.updateEmail(it) })
       } else {
           RegNewUser(
               userName = userName,
               password = password,
               onUserChangeName = { userName = it
                                  viewModel.updateEmail(userName)},
               OnUserChangePassword = {password = it},
               addUserButtonClick = {
                 viewModel.addNewUser()
                  viewModel.saveProfile() },
               addUserCancel = {navController.navigate(Routes.LoginScreen.route)})    // Avbryt och återgå till login skärmen

       }
    }


}

@Composable
fun RegNewUser(modifier: Modifier = Modifier,
               userName: String,
               password: String,
               OnUserChangePassword: (String) -> Unit,
               onUserChangeName: (String) -> Unit,
               addUserCancel: () -> Unit,
               addUserButtonClick: () -> Unit) {



    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        CustomInput(label = "Ange din E-mailadress",
            value = userName,
            icon = Icons.Default.Email,
            onValueChange = onUserChangeName)

        CustomInput(label = "Ange ditt lösenord",
            value = password,
            icon = Icons.Default.Lock,
            onValueChange = OnUserChangePassword)


        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
            CustomButton(label = "Registrera",
                onClick = addUserButtonClick,
                modifier = Modifier.padding(end = 56.dp))

            CustomButton(label = "Avbryt",
                onClick = addUserCancel)

        }
    }
}


@Composable
fun CustomButton(modifier: Modifier = Modifier, label: String, onClick: () -> Unit) {

        ElevatedButton(modifier = modifier, onClick = onClick) {
            Text(text = label)
    }


}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUserProfileScreen(){

    // lite dummys
    val navController = rememberNavController()
    val factoryUserProfileScreen: UserProfileScreenViewModelFactory =
        UserProfileScreenViewModelFactory(Reprository(firebasemanger = FirebaseManger()))


    ChattlyAppTheme {
    UserProfileScreen(navController, factory = factoryUserProfileScreen)
    }
}

@Composable
fun CustomInput(modifier: Modifier = Modifier,
                label: String,
                value: String,
                icon: ImageVector,
                onValueChange: (String)-> Unit) {     // tar emot funktion för uppdatering


    OutlinedTextField(
        value = value,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onValueChange = onValueChange,
        label = { Text(text = label) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        shape = RoundedCornerShape(30)
    )


}


