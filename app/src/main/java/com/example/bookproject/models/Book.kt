package com.example.bookproject.models

data class Book(
    val id: Int,
    val name: String,
    val description: String,
    val authors: List<String>,
    val price: String
)