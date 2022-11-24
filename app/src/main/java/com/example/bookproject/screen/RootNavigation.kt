package com.example.bookproject.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookproject.utils.Graph

@Composable
fun RootNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, route = Graph.ROOT, startDestination = Graph.HOME) {
        composable(Graph.HOME) {
            MainScreen()
        }
    }
}

