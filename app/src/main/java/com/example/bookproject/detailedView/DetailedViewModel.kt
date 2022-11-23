package com.example.bookproject.detailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookproject.models.Book

//this class is just like service in EE application
class DetailedViewModel(bookId: String) : ViewModel() {
    val bookLiveData: MutableLiveData<Book> by lazy {
        MutableLiveData<Book>()
    }

}
