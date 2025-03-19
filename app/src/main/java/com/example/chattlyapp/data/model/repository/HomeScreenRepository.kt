package com.example.chattlyapp.data.model.repository

import android.util.Log
import com.example.chattlyapp.data.model.ChatData
import com.example.chattlyapp.data.model.Messages
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeScreenRepository {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun resumeChat(chatId: String,onResult: (String) -> Unit) {

        db.collection("chats").document(chatId).get()
            .addOnSuccessListener { document ->
                if (!document.exists()) {
                    //Skapa nytt chat / document
                    val chatData = hashMapOf(
                        "chatId" to chatId,
                        //  "users" to listOf(currentUserEmail, contactEmail),
                        "timestamp" to System.currentTimeMillis()
                    )
                    db.collection("chats").document(chatId).set(chatData)
                }
                onResult(chatId)
            }
    }

    fun getLatestMessages(): Flow<List<ChatData>> = channelFlow {

        val userId =  auth.currentUser?.email ?: "Ingen användare email"

        val chatQuery = Firebase.firestore.collection("chats")
            .whereArrayContains("users", userId)
            .orderBy("timestamp", Query.Direction.DESCENDING)

        var lastSentChats: List<ChatData> = emptyList() // Håller koll på senast skickade chattar

        val chatListener = chatQuery.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val chats = mutableListOf<ChatData>()
            val totalChats = snapshot?.documents?.size ?: 0
            var counter = 0

          //  Log.d("!!!", "Total chats == $totalChats")

            if (totalChats == 0) {
                trySend(emptyList()) // Skicka tom lista om inga chattar finns
                return@addSnapshotListener
            }

            snapshot?.documents?.forEach { chatDoc ->
                val chatId = chatDoc.id

                // NYTT: Lyssna i realtid på senaste meddelandet i varje chatt!
                val messageListener = Firebase.firestore.collection("chats")
                    .document(chatId)
                    .collection("messages")
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(1)
                    .addSnapshotListener { messageSnapshot, _ ->
                        val lastMessage = messageSnapshot?.documents?.firstOrNull()?.toObject(ChatData::class.java)

                        val chatData = chatDoc.toObject(ChatData::class.java)?.copy(
                            lastMessage = lastMessage?.text ?: "Inget meddelande",
                            timestamp = lastMessage?.timestamp ?: 0L
                        )

                        chatData?.let { chats.add(it) }

                        counter++
                        if (counter == totalChats) {
                            if (chats != lastSentChats) {
                                trySend(chats)
                                lastSentChats = chats
                            }
                        }

                      //  Log.d("!!!", "Chat: ${chatData?.chatId}, LastMessage: ${chatData?.lastMessage}")
                    }

                // Lägg till awaitClose så att vi kan ta bort lyssnaren vid behov
               // awaitClose { messageListener.remove() }







            }
        }

        awaitClose { chatListener.remove() }
    }

}

