package com.secual_inc.punchout

import androidx.compose.runtime.Composable

sealed class MenuItem(val title: String, val icon: Int, val route: String, val screen: @Composable () -> Unit) {

    object Settings : MenuItem("Settings", R.drawable.ic_settings, "settings", { SettingsScreen() })

    object Favarite : MenuItem("お気に入り", R.drawable.ic_person, "favarite", { Unit })

    object Logout : MenuItem("ログアウト", R.drawable.ic_exit, "logout", { Unit })
}