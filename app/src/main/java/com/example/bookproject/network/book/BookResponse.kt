package com.example.bookproject.network.book

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("text_list")
    val authors: List<BookResponseAuthorItem>,
    @SerializedName("color")
    val price: String
)
