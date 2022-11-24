package com.example.bookproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookproject.detailedView.DetailedView
import com.example.bookproject.screen.RootNavGraph
import com.example.bookproject.settings.Settings
import com.example.bookproject.ui.theme.BookProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //navigation
                    val navController = rememberNavController()
                    
                    RootNavGraph(navHostController = navController)

//                    Scaffold(bottomBar = {
//                        BottomNavigationBar(
//                            items = listOf(
//                                //this is the button to see the bookList at the bottom
//                                BottomNavItem(
//                                    name = stringResource(id = R.string.navigation_books_title),
//                                    route = "booksList",
//                                    icon = Icons.Default.List
//                                ),
//                                //this is the button to see the settings at the bottom
//                                BottomNavItem(
//                                    name = stringResource(id = R.string.navigation_settings_title),
//                                    route = "settings",
//                                    icon = Icons.Default.Settings
//                                )
//
//                            ),
//                            navController = navController,
//                            onItemClick = {
//                                navController.navigate(it.route)
//                            }
//                        )
//                    }) {
//                        Navigation(navController = navController)
//                    }
                }
            }
        }
    }
}

//when either settings, books option is chosen in home screen,
//navigate to that @Composable automatically.
//@Composable
//fun Navigation(navController: NavHostController){
//    NavHost(navController = navController, startDestination = "booksList"){
//        composable("booksList"){
////            BooksList(
//////                onBookClick = { bookId ->
//////                    navController.navigate("detailedView/$bookId")
//////                }
////            )
//        }
//
//        composable("settings"){
//            Settings()
//        }
//
//        //one of the books in the home screen is clicked
//        composable("detailedView/{bookId}"){
//            backStackEntry ->
//            backStackEntry.arguments?.getString("bookId")?.let { DetailedView(it) }
//        }
//    }
//}



