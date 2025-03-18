package com.example.chattlyapp.viewmodel

import com.example.chattlyapp.data.ChatData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ChatRepository() {

    private val db = Firebase.firestore
    private val auth = Firebase.auth

   fun StartChatWithUser(contactEmail: String, onResult: (String)-> Unit){
      val currentUserEmail = auth.currentUser?.email ?: return   //inte inloggad hejdå
      val chatId = if(currentUserEmail < contactEmail) "${currentUserEmail}_${contactEmail}"
        else "${contactEmail}_${currentUserEmail}"      //sortera email bokstavsorning så att id alltid blir lika för samma två personer  A

       db.collection("chats").document(chatId).get()
           .addOnSuccessListener { document ->
               if(!document.exists()){
                   //Skapa nytt chat / document
                   val chatData = hashMapOf(
                       "chatId" to chatId,
                       "users" to listOf(currentUserEmail,contactEmail),
                       "lastMessage" to "",
                       "timestamp" to System.currentTimeMillis()
                   )
                db.collection("chats").document(chatId).set(chatData)
               }
               onResult(chatId)
           }


    }




}



