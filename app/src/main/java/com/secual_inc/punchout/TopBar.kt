package com.secual_inc.punchout

import android.util.Log
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.secual_inc.punchout.ui.theme.PunchOutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    TopAppBar(
        title = {},
        navigationIcon = if (navBackStackEntry?.destination?.route == "home") {
            {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(Icons.Filled.Menu, "")
                }
            }
        } else {
            {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Filled.ArrowBack, "")
                }
            }
        },
        backgroundColor = Color(red = 1, green = 172, blue = 200),
        contentColor = Color.White,
        elevation = 0.dp
    )
}

@Preview
@Composable
fun TopBarPreview() {

    PunchOutTheme {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        val scope = rememberCoroutineScope()

        val navController = rememberNavController()

        TopBar(scope, scaffoldState, navController)
    }
}
