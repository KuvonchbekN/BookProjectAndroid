package com.example.bookproject.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStore
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookproject.detailedView.DetailedView
import com.example.bookproject.list.BooksList
import com.example.bookproject.settings.Settings

@Composable
fun BottomBarGraph(navHostController: NavHostController ){
    NavHost(navController = navHostController, route = "home", startDestination = "bookList"){
        composable("bookList"){
            BooksList(navHostController)
        }

        composable("settings"){
            Settings()
        }

        detailNavGraph()
    }
}

fun NavGraphBuilder.detailNavGraph(){
    navigation(route = "detail/{bookId}", startDestination = "detail"){
        composable("detail", arguments = listOf(
            navArgument(
                name = "bookId"
            ){
                type = NavType.StringType
            }
        )){
            val bookId = it.arguments?.getString("bookId")?:""
            DetailedView(bookId = bookId)
        }
    }
}