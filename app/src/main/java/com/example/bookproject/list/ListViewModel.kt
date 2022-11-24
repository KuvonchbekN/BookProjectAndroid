package com.example.bookproject.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookproject.models.Book
import com.example.bookproject.network.RetrofitInstance
import com.example.bookproject.network.book.BookResponse
import com.example.bookproject.network.response.MyListResponse
import com.example.bookproject.utils.Constants
import com.example.bookproject.utils.getListOfAuthorsFromResponse
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {
    val booksLiveData : MutableLiveData<List<Book>> by lazy { //this is what we will use in BooksList
        MutableLiveData<List<Book>>()
    }

    init {
        getAllBooksFromDB()
    }

    private fun getAllBooksFromDB() {
        viewModelScope.launch {
            try {
                val response: MyListResponse<BookResponse> =
                    RetrofitInstance.bookService.getAllBooks(Constants.STUDENT_ID)

                val data = response.data

                if (data!=null){
                    val myBooks = mutableListOf<Book>() //this is just simple arrayList

                    for (bookFromResponse in data){
                        bookFromResponse.description?.let {
                            Book(
                                bookFromResponse.id,
                                bookFromResponse.name,
                                it,
                                getListOfAuthorsFromResponse(bookFromResponse.authors),
                                bookFromResponse.price
                            ).let {
                                myBooks.add(it)
                            }
                        }
                    }

                    booksLiveData.value = myBooks
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }


}