package com.andis.kigar.navigation

sealed class Screen(val route: String) {
    object Splash:Screen(route = "splash_screen")
    object AddData:Screen(route = "add_data_screen")
    object ListData:Screen(route = "list_data_screen")
    object Login:Screen(route = "login_screen")
}
