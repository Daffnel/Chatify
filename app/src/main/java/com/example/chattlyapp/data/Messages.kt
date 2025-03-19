package com.example.chattlyapp.data

data class Messages (val senderId: String = "",  // Vem skickade meddelandet
                     val text: String = "",      // Meddelandets innehåll
                     val timestamp: Long = 0L)  {  // När meddelandet skickades)
}