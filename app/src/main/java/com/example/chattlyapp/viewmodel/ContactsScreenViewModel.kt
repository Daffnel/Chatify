package com.example.chattlyapp.viewmodel

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chattlyapp.data.UserInfoFromContacts

class ContactsScreenViewModel: ViewModel() {

    val contactList = mutableListOf<UserInfoFromContacts>()

    /* Läser in all kontakter i en array match sedan email mot FB för att hitta användare */
    fun getContact(context: Context): List<UserInfoFromContacts> {


        val cursor: Cursor? =
            context.contentResolver.query(            //Cursor hanterar läsning i från databasen
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Email.DISPLAY_NAME_PRIMARY,
                    ContactsContract.CommonDataKinds.Email.ADDRESS
                ),
                null,                       //inga fler frågor till databasen
                null,
                ContactsContract.CommonDataKinds.Email.DISPLAY_NAME_PRIMARY + " ASC" //Sortera i bokstavsordning
            )

        cursor?.use { data ->
            val name =  data.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME_PRIMARY)  //funkar detta men Primary
            val email = data.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS)


            while (data.moveToNext()) {
                val firstAndLastName  = data.getString(name)  // Hämta namn
                val email2 = data.getString(email) // Hämta e-postadress

               val parts = firstAndLastName.split(" ") //dela namnet vid mellanslag
               val firstName = parts.getOrNull(0) ?: ""
                val lastName = parts.getOrNull(1) ?: ""


                contactList.add(UserInfoFromContacts(firstName = firstName, lastName = lastName, eMail = email2))

            }
        }

        cursor?.close()
        return contactList
    }


//jämför användarens mailadress med mailadreserna i telefonboken för att hitta en chat kompisr

/*fun findMatchingUser() {


    reprository.fetchUsers { firebaseUsers ->
        val commonUsers = firebaseUsers.filter { it.eMail in contactList.map { user -> user.eMail } }
        Log.d("!!!", "Gemensamma användare: $commonUsers")
    }

}*/

}
