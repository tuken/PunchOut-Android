package com.secual_inc.punchout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.secual_inc.punchout.ui.theme.PunchOutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuDrawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController, menus: List<MenuItem>) {

    Column(
        modifier = Modifier
            .background(Color(red = 32, green = 32, blue = 32))
    ) {

        Image(
            painter = painterResource(id = R.drawable.secual),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route

        menus.forEach { m ->

            MenuItemDrawer(item = m, onItemClick = {

                navController.navigate(m.route)
//                navController.navigate(m.route) {
//
//                    navController.graph.startDestinationRoute?.let { route ->
//                        popUpTo(route) {
//                            saveState = true
//                        }
//                    }
//
//                    launchSingleTop = true
//                    restoreState = true
//                }

                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        MenuItemDrawer(item = MenuItem.Logout, onItemClick = {

            scope.launch {
                scaffoldState.drawerState.close()
            }
        })

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Powered by Secual Inc.",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun DrawerPreview() {

    PunchOutTheme {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

        val scope = rememberCoroutineScope()

        val navController = rememberNavController()

        MenuDrawer(scope, scaffoldState, navController, listOf(MenuItem.Settings))
    }
}
