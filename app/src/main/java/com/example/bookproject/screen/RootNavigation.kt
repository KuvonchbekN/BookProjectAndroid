package com.example.bookproject.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(navController =navHostController, route = "home", startDestination = "bookList"){
        composable("bookList"){
            MainScreen(navHostController)
        }
    }
}

