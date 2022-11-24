package com.example.bookproject.network.book

import com.google.gson.annotations.SerializedName

//this class is used to send the request to the api
data class BookRequest(
    @SerializedName("title")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("text_list")
    val authors: List<String>,

    @SerializedName("color")
    val price: String
)
