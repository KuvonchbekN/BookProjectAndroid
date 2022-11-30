package com.example.bookproject.detailedView

import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookproject.MainActivity
import com.example.bookproject.R
import com.example.bookproject.edit.EditActivity


//this is itself one big composable and inside of it there are some items like name, description, price and etc.
@Composable
fun DetailedView(bookId: String, viewModel: DetailedViewModel = DetailedViewModel(bookId)) {

    val book by viewModel.bookLiveData.observeAsState()
    val context = LocalContext.current

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
            }

            Description(description = book!!.description)
            Spacer(modifier = Modifier.height(16.dp))
            Price(price = book!!.price)
            CustomDivider()
            Spacer(modifier = Modifier.height(16.dp))
            if (!book!!.authors.isNullOrEmpty()) {
                Authors(authors = book!!.authors)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, EditActivity::class.java)
                        intent.putExtra("data", bookId)
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.edit_button))
                }


                Spacer(modifier = Modifier.width(10.dp))
                val deletedMsg = stringResource(id = R.string.deleted_successfully_msg)
                Button(
                    onClick = {
                        viewModel.deleteOneBookById(bookId)
                        val toast = Toast.makeText(context, deletedMsg,Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.show()

                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as MainActivity).finishAffinity()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = R.string.delete_button))
                }
            }
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
        fontSize = 12.sp,
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
    Column(modifier = Modifier.fillMaxWidth()) {
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

















