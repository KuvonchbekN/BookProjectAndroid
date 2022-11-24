package com.example.bookproject.detailedView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookproject.R


//this is itself one big composable and inside of it there are some items like name, description, price and etc.
@Composable
fun DetailedView(bookId: String, viewModel: DetailedViewModel = DetailedViewModel(bookId)) {

    val book by viewModel.bookLiveData.observeAsState()

    if (book != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white_background))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Name(name = book!!.name)
                Spacer(modifier = Modifier.weight(1f))
                Price(price = book!!.price)
            }

            Description(description = book!!.description)
            Spacer(modifier = Modifier.height(16.dp))
            CustomDivider()
            Spacer(modifier = Modifier.height(16.dp))
            if (!book!!.authors.isNullOrEmpty()){
                Authors(authors = book!!.authors!!)
            }

            //TODO implement edit button here

            //EditButton {
//                viewModel.editMovieById(movieId, MovieRequest(
//                    movie!!.name,
//                    "test desc",
//                    movie!!.actors,
//                    movie!!.budget))
//            }

        }
    }


}

@Composable
private fun Name(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 30.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center
    )
}


@Composable
private fun Price(price: String) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.price_of_book, price),
        color = Color.Black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}


@Composable
private fun Description(description: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = description,
        color = Color.DarkGray,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun Authors(authors: List<String>) {
    Column(modifier = Modifier.fillMaxSize()) {
        for (author in authors) {
            AuthorNameView(author = author)
        }
    }
}


@Composable
private fun AuthorNameView(author: String) {
    Text(
        modifier = Modifier.padding(6.dp, 3.dp),
        text = "- $author",
        color = Color.DarkGray,
        fontSize = 19.sp,
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun CustomDivider() {
    Divider(
        color = Color.LightGray
    )
}



//todo I can implement edit button here
//@Composable
//private fun EditButton(onClick: () -> Unit) {
//    Button(
//        onClick = {
//            onClick()
//        },
//        modifier = Modifier
//            .width(150.dp)
//            .height(75.dp)
//            .padding(vertical = 16.dp)
//    ) {
//        Text(
//            text = stringResource(id = R.string.edit_movie_button_text)
//        )
//    }
//}

















