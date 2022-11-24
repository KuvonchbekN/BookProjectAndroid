package com.example.bookproject.utils

import com.example.bookproject.network.book.BookResponseAuthorItem

fun getListOfAuthorsFromResponse(
    authorsInResponse: List<BookResponseAuthorItem>
) : List<String>{
    val myAuthors = mutableListOf<String>()

    for (author in authorsInResponse){
        myAuthors.add(author.authorName);
    }

    return myAuthors;
}

fun splitAuthorsInputtedByComma(authorsInput:String):List<String>{
    return authorsInput.split(",")
}