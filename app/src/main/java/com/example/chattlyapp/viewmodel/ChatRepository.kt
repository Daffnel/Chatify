package com.example.chattlyapp.viewmodel

import android.os.Message
import android.util.Log
import com.example.chattlyapp.data.Messages

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

import kotlinx.coroutines.flow.channelFlow

import kotlinx.coroutines.launch

class ChatRepository() {


    private val db = Firebase.firestore
    private val auth = Firebase.auth

    fun setChatId(contactEmail: String): String {
        val currentUserEmail = auth.currentUser?.email ?: " "//return   //inte inloggad hejdå
        val chatId = if (currentUserEmail < contactEmail) "${currentUserEmail}_${contactEmail}"
        else "${contactEmail}_${currentUserEmail}"      //sortera email bokstavsorning så at

        Log.d("!!!", "Id i repro = ${chatId}")
        return chatId
    }

    fun StartChatWithUser(contactEmail: String, onResult: (String) -> Unit) {
        val currentUserEmail = auth.currentUser?.email ?: return   //inte inloggad hejdå
        val chatId = if (currentUserEmail < contactEmail) "${currentUserEmail}_${contactEmail}"
        else "${contactEmail}_${currentUserEmail}"      //sortera email bokstavsorning så att id alltid blir lika för samma två personer  A

        db.collection("chats").document(chatId).get()
            .addOnSuccessListener { document ->
                if (!document.exists()) {
                    //Skapa nytt chat / document
                    val chatData = hashMapOf(
                        "chatId" to chatId,
                        "users" to listOf(currentUserEmail, contactEmail),
                        "timestamp" to System.currentTimeMillis()
                    )
                    db.collection("chats").document(chatId).set(chatData)
                }
                onResult(chatId)
            }
    }

    fun sendMessage(chatId: String, message: String) {
        val messageData = hashMapOf(
            "senderId" to Firebase.auth.currentUser?.email,
            "text" to message,
            "timestamp" to System.currentTimeMillis()
        )
        db.collection("chats").document(chatId)
            .collection("messages")
            .add(messageData)
    }


    fun getMessages(chatId: String): Flow<List<Messages>> = channelFlow {

        Log.d("!!!","ChatId i repro == ${chatId}")
        val listener = Firebase.firestore.collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("!!!", "Fel vid hämtning av meddelanden", error)
                    close(error) // Stänger flödet vid fel
                    return@addSnapshotListener
                }
                if (snapshot == null || snapshot.isEmpty) {
                    Log.d("!!!", "Snapshot är null eller tom")
                } else {
                    Log.d("!!!", "Snapshot-dokument: ${snapshot.documents}")
                }


                val messages = snapshot?.documents?.mapNotNull { it.toObject(Messages::class.java) } ?: emptyList()
                Log.d("!!!","Meddelanden efter mappning ${messages}")
                launch { send(messages) } // ✅ Skickar listan med meddelanden
            }

        awaitClose { listener.remove() } // Stänger Firestore-lyssnaren vid cleanup
    }




}


