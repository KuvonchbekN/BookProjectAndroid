package com.example.bookproject.network.book

import com.google.gson.annotations.SerializedName

data class BookResponseAuthorItem(
    @SerializedName("id")
    val authorEntryId: String,
    @SerializedName("record_id")
    val bookRecordId: String,
    @SerializedName("value")
    val authorName: String
)
