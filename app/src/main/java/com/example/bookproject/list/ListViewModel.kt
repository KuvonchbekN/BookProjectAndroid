package com.example.bookproject.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookproject.models.Book

class ListViewModel: ViewModel() {
    val booksLiveData : MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>()
    }
}