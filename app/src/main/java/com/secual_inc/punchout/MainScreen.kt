package com.secual_inc.punchout

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secual_inc.punchout.ui.theme.PunchOutTheme


fun customShape() = object : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Rectangle(Rect(0f, 0f, size.width * 2 / 3 /* width */, size.height /* height */))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val scope = rememberCoroutineScope()

    val navController = rememberNavController()

    val menus = listOf<MenuItem>(MenuItem.Settings)

    ModalBottomSheetLayout(sheetContent = {
        BottomSheetContent()
    },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color(red = 1, green = 172, blue = 200)) {

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
                startDestination = "splash",
            ) {

                composable("splash") {
                    SplashScreen(navController)
                }

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

                        HomeScreen(scope = scope, sheetState = modalBottomSheetState)
                    }
                }

                menus.forEach { m ->
                    composable(route = m.route, content = m.screen)
                }
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

@Composable
fun BottomSheetListItem(icon: Int, title: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(title) })
            .height(100.dp)
            .background(color = Color(red = 1, green = 172, blue = 200))
            .padding(start = 15.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "Share", tint = Color.White)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = title, color = Color.White)
    }
}

@Composable
fun BottomSheetContent() {
    val context = LocalContext.current
    Column {
        BottomSheetListItem(
            icon = R.drawable.ic_person,
            title = "Share",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = R.drawable.ic_settings,
            title = "Get link",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
//        BottomSheetListItem(
//            icon = R.drawable.ic_exit,
//            title = "Edit name",
//            onItemClick = { title ->
//                Toast.makeText(
//                    context,
//                    title,
//                    Toast.LENGTH_SHORT
//                ).show()
//            })
//        BottomSheetListItem(
//            icon = R.drawable.ic_person,
//            title = "Delete collection",
//            onItemClick = { title ->
//                Toast.makeText(
//                    context,
//                    title,
//                    Toast.LENGTH_SHORT
//                ).show()
//            })
    }
}