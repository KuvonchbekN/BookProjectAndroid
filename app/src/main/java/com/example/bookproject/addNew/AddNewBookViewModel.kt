package com.example.bookproject.addNew

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookproject.network.RetrofitInstance
import com.example.bookproject.network.book.BookRequest
import com.example.bookproject.network.response.MyResponse
import com.example.bookproject.utils.Constants
import kotlinx.coroutines.launch

//this is the layer between front-end and backend
class AddNewBookViewModel : ViewModel() {
    val bookInsertResponse: MutableLiveData<MyResponse> by lazy {  //this is always used in front-end, exactly in the class which composables exist!, coz we need to show the data to the user
        MutableLiveData<MyResponse>()
    }

    fun saveNewBookToRemoteDb(book: BookRequest) {
        viewModelScope.launch {
            try {

                val response: MyResponse = RetrofitInstance.bookService.insertNewBook(
                    Constants.STUDENT_ID,
                    book
                )

                bookInsertResponse.value = response;

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}