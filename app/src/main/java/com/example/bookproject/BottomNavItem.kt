package com.example.bookproject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route : String,
    val imageVector: ImageVector
){
    object BookList : BottomNavItem(
        "bookList",
        Icons.Default.Menu
    )

    object Settings: BottomNavItem(
        "settings",
        Icons.Default.Settings
    )

}
