package com.example.chattlyapp.screens.contacts

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chattlyapp.data.model.UserInfoFromContacts
import com.example.chattlyapp.data.model.repository.Reprository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ContactsScreenViewModel(private val repo: Reprository): ViewModel() {

    val contactList = mutableListOf<UserInfoFromContacts>()

    private val _firebaseUsers = MutableStateFlow<List<UserInfoFromContacts>>(emptyList()) // Variabel för att lagra data
    val firebaseUsers: StateFlow<List<UserInfoFromContacts>> = _firebaseUsers // Exponeras som StateFlow

    init {
        fetchUser()     //läser in datan det första vi gör
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _firebaseUsers.value = repo.fetchUser()
        }
    }



    // skapen lista med alla som är användare markerade

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
            val name =
                data.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME_PRIMARY)  //funkar detta men Primary
            val email = data.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS)


            while (data.moveToNext()) {
                val firstAndLastName = data.getString(name)  // Hämta namn
                val email2 = data.getString(email) // Hämta e-postadress

                val parts = firstAndLastName.split(" ") //dela namnet vid mellanslag
                val firstName = parts.getOrNull(0) ?: ""
                val lastName = parts.getOrNull(1) ?: ""
                contactList.add(
                    UserInfoFromContacts(
                        firstName = firstName,
                        lastName = lastName,
                        email = email2,
                        isUser = false
                    )
                )

            }
        }

        cursor?.close()

        /* Marker alla användare som matchar med email och sätter isUser = True */
        return contactList.map{contact ->
            UserInfoFromContacts(
                firstName = contact.firstName,
                lastName = contact.lastName,
                email = contact.email,
                nickName = firebaseUsers.value.find{it.email == contact.email}?.nickName ?: "",
                isUser = firebaseUsers.value.any(){it.email == contact.email}, // isUser sätts till true eller false här
                userID = firebaseUsers.value.find { it.email == contact.email }?.userID ?: "Saknar Id"  //Krångligt men funkar
            )

        }


    }



}
