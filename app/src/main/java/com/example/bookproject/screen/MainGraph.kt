package com.example.bookproject.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStore
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookproject.BottomNavItem
import com.example.bookproject.detailedView.DetailedView
import com.example.bookproject.list.BooksList
import com.example.bookproject.settings.Settings
import com.example.bookproject.utils.Graph

@Composable
fun BottomBarGraph(navHostController: NavHostController ){
    NavHost(navController = navHostController, route = Graph.HOME, startDestination = BottomNavItem.BookList.route){
        composable(route = BottomNavItem.BookList.route){
            BooksList(navHostController)
        }

        composable(route = BottomNavItem.Settings.route){
            Settings()
        }
        detailNavGraph()
    }
}

fun NavGraphBuilder.detailNavGraph(){
    navigation(route = "${Graph.DETAILS}/{bookId}", startDestination = "DETAIL"){
        composable(route = "DETAIL", arguments = listOf(
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