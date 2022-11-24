package com.example.bookproject.screen

import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.bookproject.R

@Composable
fun MainScreen(navHostController: NavHostController){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.list_add_new_text))
            }
        },
        bottomBar = {
            BottomBar(navController = navHostController)
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
            BottomBarGraph(navHostController = navHostController)
    }
}

