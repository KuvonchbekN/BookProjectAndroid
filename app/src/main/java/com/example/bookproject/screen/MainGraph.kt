package com.example.bookproject.screen

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bookproject.BottomNavItem
import com.example.bookproject.detailedView.DetailedView
import com.example.bookproject.list.BooksList
import com.example.bookproject.settingsScreen.AskLocationPermission
import com.example.bookproject.settingsScreen.Settings
import com.example.bookproject.utils.Graph

@OptIn(ExperimentalMaterialApi::class)
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