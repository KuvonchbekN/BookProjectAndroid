package com.example.bookproject.screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(navHostController: NavHostController = rememberNavController()){
    Scaffold(
        bottomBar = {
            BottomBar(navController = navHostController)
        }
    ) {
            BottomBarGraph(navHostController = navHostController)
    }
}

