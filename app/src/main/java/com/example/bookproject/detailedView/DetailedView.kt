package com.example.bookproject.detailedView

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun DetailedView(bookId: String, viewModel: DetailedViewModel = DetailedViewModel(bookId)){

    val book by viewModel.bookLiveData.observeAsState()

}