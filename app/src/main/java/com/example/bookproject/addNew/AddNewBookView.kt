package com.example.bookproject.addNew

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.bookproject.MainActivity
import com.example.bookproject.R
import com.example.bookproject.network.book.BookRequest
import com.example.bookproject.network.response.MyResponse
import com.example.bookproject.utils.splitAuthorsInputtedByComma


@Composable
fun AddNewBookView(viewModel: AddNewBookViewModel = AddNewBookViewModel()) {

    val context = LocalContext.current

    val name = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    val authors = remember {
        mutableStateOf("")
    }

    val price = remember {
        mutableStateOf("")
    }

    val isProgressVisible = remember {
        mutableStateOf(false)
    }

    val response by viewModel.bookInsertResponse.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.book_list_b_color))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        NameInput(name = name.value, onNameChange = { name.value = it })
        Spacer(Modifier.height(16.dp))
        DescriptionInput(
            description = description.value,
            onDescriptionChange = { description.value = it })
        Spacer(Modifier.height(16.dp))
        AuthorsInput(authors = authors.value, onAuthorsChange = { authors.value = it })
        Spacer(Modifier.height(16.dp))
        PriceInput(price = price.value, onPriceChange = { price.value = it })
        Spacer(Modifier.height(16.dp))

        val notValidMessage = stringResource(id = R.string.inputs_not_valid)
        AddNewButton {
            if (isInputValid(name.value, description.value, authors.value, price.value)) {
                viewModel.saveNewBookToRemoteDb(
                    BookRequest(
                        name = name.value,
                        description = description.value,
                        splitAuthorsInputtedByComma(authors.value),
                        price.value
                    )
                )
                isProgressVisible.value = true;
            }else{
                val toast = Toast.makeText(context, notValidMessage,Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }

    }

    response?.let { ProgressWidget(response = it, isVisible= isProgressVisible.value, context) }

}

@Composable
private fun ProgressWidget(response: MyResponse, isVisible: Boolean, context: Context) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Text(
                modifier = Modifier
                    .background(colorResource(id = R.color.add_new_movie_text_color))
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 25.sp,
                text =
                if (response.status.isEmpty()) stringResource(id = R.string.add_new_in_progress_mgs) //by default status is "", so if it is empty that means network request hasn't returned a response yet
                else if (response.status == "OK") stringResource(id = R.string.add_new_saved_successfully_msg)
                else stringResource(id = R.string.add_new_failed_to_save_msg)
            )
        }

        context.startActivity(Intent(context, MainActivity::class.java))
    }
}

@Composable
private fun NameInput(name: String, onNameChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray),
        value = name,
        onValueChange = { onNameChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = {
            Text(stringResource(id = R.string.add_new_name_input_placeholder))
        }
    )
}


@Composable
private fun DescriptionInput(description: String, onDescriptionChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color.LightGray),
        value = description,
        onValueChange = { onDescriptionChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = {
            Text(stringResource(id = R.string.add_new_description_placeholder))
        }
    )
}


@Composable
private fun AuthorsInput(authors: String, onAuthorsChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color.LightGray),
        value = authors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onAuthorsChange(it) },
        label = {
            Text(stringResource(id = R.string.add_new_authors_placeholder))
        }
    )
}

@Composable
private fun PriceInput(price: String, onPriceChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray),
        value = price,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onPriceChange(it) },
        label = {
            Text(stringResource(id = R.string.add_new_price_placeholder))
        }
    )
}

@Composable
private fun AddNewButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(vertical = 16.dp)
    ) {
        Text(text = stringResource(id = R.string.save_button_text))
    }
}

private fun isInputValid(
    name: String,
    description: String,
    authors: String,
    price: String
): Boolean {
    if (name.isBlank() || description.isBlank() || authors.isBlank()) return false

    if (price.isBlank() || !price.isDigitsOnly()) return false

    return true
}















