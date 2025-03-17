package com.example.chattlyapp.viewmodel

import com.example.chattlyapp.data.ChatData
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ChatRepository() {

    private val db = FirebaseFirestore.getInstance()

    fun getOrCreateChat(user1: String, user2: String, onResult: (String) -> Unit) {
        var chatId: String = ""

        if (user1 < user2) {
            chatId = "$user1-$user2"
        } else {
            chatId = "$user2-$user1"
        }

        Firebase.firestore.collection("chats")
            .document(chatId).get()
            .addOnSuccessListener { document ->
                if (!document.exists()) {   //finns inte dokumenten skapar vi de
                    val chatData = hashMapOf(
                        "chatId" to chatId,
                        "users" to listOf(user1, user2),
                        "Lastmessage" to "",
                        "timestamp" to System.currentTimeMillis()
                    )
                    Firebase.firestore.collection("chats").document(chatId).set(chatData)
                }
            }
    }


}



