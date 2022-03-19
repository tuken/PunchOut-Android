package com.secual_inc.punchout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.secual_inc.punchout.ui.theme.PunchOutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuDrawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {

    Column(modifier = Modifier
        .background(Color(red = 32, green = 32, blue = 32))) {
//        .width(300.dp)
//        .fillMaxHeight()) {

        // Header
//        Image(
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = R.drawable.logo.toString(),
//            modifier = Modifier
//                .height(100.dp)
//                .fillMaxWidth()
//                .padding(10.dp)
//        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp))

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        MenuDrawerItem(route = "main", title = "Profile", icon = R.drawable.ic_person, onItemClick = {

//            navController.navigate(it.route) {
//
//                navController.graph.startDestinationRoute?.let { route ->
//                    popUpTo(route) {
//                        saveState = true
//                    }
//                }
//
//                launchSingleTop = true
//                restoreState = true
//            }

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

@Composable
fun MenuDrawerItem(route: String, title: String, icon: Int, onItemClick: (String) -> Unit) {

//    val background = if (selected) R.color.teal_200 else android.R.color.transparent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(route) })
            .height(45.dp)
            .background(colorResource(id = android.R.color.transparent))
            .padding(start = 10.dp)
    ) {

        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )

        Spacer(modifier = Modifier.width(7.dp))

        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White
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

        MenuDrawer(scope, scaffoldState, navController)
    }
}
