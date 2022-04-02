package com.secual_inc.punchout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secual_inc.punchout.ui.theme.PunchOutTheme


fun customShape() = object : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Rectangle(Rect(0f, 0f, size.width * 2 / 3 /* width */, size.height /* height */))
    }
}

@Composable
fun MainScreen() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val scope = rememberCoroutineScope()

    val navController = rememberNavController()

    val menus = listOf<MenuItem>(MenuItem.Settings)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState, navController = navController) },
        drawerBackgroundColor = Color(red = 32, green = 32, blue = 32),
//        drawerShape = customShape(),
        drawerContent = {
            MenuDrawer(scope = scope, scaffoldState = scaffoldState, navController = navController, menus = menus)
        },
    ) {

        NavHost(
            navController = navController,
            startDestination = "home",
        ) {

            composable("home") {

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillHeight
                    )

                    HomeScreen()
                }
            }

            menus.forEach { m ->
                composable(route = m.route, content = m.screen)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {

    PunchOutTheme {

        MainScreen()
    }
}
