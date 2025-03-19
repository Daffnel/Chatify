package com.example.chattlyapp.data.model

data class ChatData (
    val chatId: String = "" ,
    val users: List<String> ,
    val senderId: String = "",
    val lastMessage: String = "",
    val timestamp: Long = 0L,
    val message: String = "",
    val text: String = ""
){
constructor(): this("", emptyList(),"","",0,"","")



}