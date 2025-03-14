package com.example.chattlyapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chattlyapp.viewmodel.UserProfileScreenViewModel

@Composable
fun UserProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UserProfileScreenViewModel = viewModel()
) {


    Column(
        modifier = Modifier
            .padding(top = 42.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
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

       CustomInput(label = "E-Post",
           value = viewModel.userProfile.eMail,
           icon = Icons.Default.Email,
           onValueChange = {viewModel.updateEmail(it)}
           )
    }

}


@Composable
fun CustomButton(modifier: Modifier = Modifier, label: String, onClick: () -> Unit) {

    ElevatedButton(onClick = onClick) {
        Text(text = label)
    }


}

@Preview(showBackground = true)
@Composable
fun RegisterScreen() {
    val navController = rememberNavController()  // Skapar en dummy-navController
    UserProfileScreen(navController = navController)
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


