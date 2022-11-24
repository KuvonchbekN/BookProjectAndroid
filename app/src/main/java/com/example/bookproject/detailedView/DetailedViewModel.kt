package com.example.bookproject.detailedView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookproject.models.Book
import com.example.bookproject.network.RetrofitInstance
import com.example.bookproject.network.book.BookRequest
import com.example.bookproject.network.book.BookResponse
import com.example.bookproject.network.response.MyItemResponse
import com.example.bookproject.network.response.MyResponse
import com.example.bookproject.utils.Constants
import com.example.bookproject.utils.getListOfAuthorsFromResponse
import com.example.bookproject.utils.splitAuthorsInputtedByComma
import kotlinx.coroutines.launch
import retrofit2.Retrofit

//this is the layer between backend and frontend in the application
class DetailedViewModel(bookId: String) : ViewModel() {
    val bookLiveData: MutableLiveData<Book> by lazy {
        MutableLiveData<Book>()
    }

    init{
        getBookById(bookId)
    }

    private fun getBookById(bookId: String) {
        viewModelScope.launch {
            try {
                val response: MyItemResponse<BookResponse> =
                    RetrofitInstance.bookService.getBookById(bookId, Constants.STUDENT_ID)

                val bookFromResponse = response.data

                if (bookFromResponse != null) {
                    bookLiveData.value = Book(
                        bookFromResponse.id,
                        bookFromResponse.name,
                        bookFromResponse.description ?: "",
                        getListOfAuthorsFromResponse(bookFromResponse.authors),
                        bookFromResponse.price
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun editBookById(bookId: String, bookRequest: BookRequest) {
        viewModelScope.launch {
            try {
                val updateOneBookById = RetrofitInstance.bookService.updateOneBookById(
                    bookId, Constants.STUDENT_ID, bookRequest
                )
                Log.d("Update Response", updateOneBookById.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteOneBookById(bookId: String) {
        viewModelScope.launch {
            try {
                val response: MyResponse =
                    RetrofitInstance.bookService.deleteOneBookById(bookId, Constants.STUDENT_ID)
                Log.d("Deleted book response: ", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
