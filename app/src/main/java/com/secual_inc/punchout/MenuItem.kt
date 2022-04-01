package com.secual_inc.punchout

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

sealed class MenuItem(val title: String, val icon: Int, val route: String, val screen: @Composable (NavBackStackEntry) -> Unit) {

    object Settings : MenuItem("Settings", R.drawable.ic_settings, "settings", { SettingsScreen() })

    object Favarite : MenuItem("Favorite", R.drawable.ic_person, "favarite", { Unit })

    object Logout : MenuItem("Logout", R.drawable.ic_exit, "logout", { Unit })
}