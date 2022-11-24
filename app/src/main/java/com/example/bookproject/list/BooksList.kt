package com.example.bookproject.list

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookproject.R
import com.example.bookproject.addNew.AddNewActivity
import com.example.bookproject.models.Book

@Composable
fun BooksList(
    onBookClick: (String) -> Unit = {},
    viewModel: ListViewModel = ListViewModel()
) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        val books by viewModel.booksLiveData.observeAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .background(colorResource(id = R.color.book_list_b_color))
                .padding(0.dp, 0.dp, 0.dp, 50.dp)
        ) {
            books?.let {
                items(items = it.toList(), itemContent = { item ->
                    BookItem(book = item, onBookClick)
                })
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 70.dp),
            onClick = { context.startActivity(Intent(context, AddNewActivity::class.java)) }) {
            Text(
                stringResource(id = R.string.list_add_new_text),
                modifier = Modifier.padding(15.dp, 5.dp),
                color = colorResource(id = R.color.add_new_movie_text_color)
            )
        }
    }

}

@Composable
fun BookItem(book: Book, onBookClick: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp, 10.dp, 15.dp, 0.dp)
        .clickable { onBookClick(book.id.toString()) }) {
        Name(name = book.name)
        Description(description = book.description)
        Divider(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(Color.LightGray)
        )
    }
}

@Composable
fun Name(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Description(description: String) {
    Text(
        text = description,
        color = Color.DarkGray,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}
