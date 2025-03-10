package com.example.chattlyapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chattlyapp.data.UserProfile

@Composable
fun MainScreenRegUser(modifier: Modifier = Modifier){

   /* var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var nickName by remember { mutableStateOf("") }
    var eMail by remember { mutableStateOf("") }
*/

    var firstName: String = ""
    var lastName: String = ""
    var nickName: String = ""
    var eMail: String = ""
    var password: String = ""

    var userInfo = UserProfile()

    Column(
        modifier = Modifier
            .padding(top = 42.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        firstName = CustomInput(label = "Föramn", icon = Icons.Default.Person)
        lastName = CustomInput(label = "Efternamn", icon = Icons.Default.Face)
        nickName = CustomInput(label = "Nickname", icon = Icons.Default.Person)
        eMail = CustomInput(label = "E-post", icon = Icons.Default.Email)
        password = CustomInput(label = "Lösenord", icon = Icons.Default.Lock)



        CustomButton(label = "Lägga till") {
            adddUserProfile(firstName, lastName, nickName, eMail, password)
            Log.d("!!!","Custom button clicked")
        }


    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview(){

    MainScreenRegUser()
}
@Composable
fun CustomButton(modifier: Modifier = Modifier,label: String, onClick: () -> Unit){

    ElevatedButton(onClick = onClick,) {
        Text(text = label)
    }


}
@Composable
fun CustomInput(modifier: Modifier = Modifier,label: String,icon: ImageVector ): String{

    var inputValue by remember { mutableStateOf("") }


    OutlinedTextField(
        value = inputValue,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onValueChange = { inputValue = it },
        label = { Text(text = label) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        shape = RoundedCornerShape(30)
    )

return inputValue
}

fun adddUserProfile(firstName: String, lastName: String, nickName: String, eMail: String, password: String){

    val useProfile = UserProfile(
        firstName = firstName,
        lastName = lastName,
        nickName = nickName,
        eMail = eMail,
        password = password)

}


