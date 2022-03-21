package com.secual_inc.punchout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secual_inc.punchout.ui.theme.PunchOutTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            PunchOutTheme {

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

                    MainScreen()
                }
            }
        }
    }
}

fun customShape() = object : Shape {

    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Rectangle(Rect(0f, 0f, size.width*2/3 /* width */, size.height /* height */))
    }
}

@Composable
fun MainScreen() {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val scope = rememberCoroutineScope()

    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
        drawerBackgroundColor = Color(red = 32, green = 32, blue = 32),
//        drawerShape = customShape(),
        drawerContent = {
            MenuDrawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
    ) {

        NavHost(
            navController = navController,
            startDestination = "main"
        ) {

            composable("main") {

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(red = 1, green = 172, blue = 200)),
                    contentAlignment = Alignment.Center,
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.common_logo),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillHeight
                    )

                    Column {

                        CalendarScreen()

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {

                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .height(50.dp)
                                    .background(Color.Transparent),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.White,
                                )
                            ) {

                                Text(
                                    text = "PUNCH ME IN",
                                )
                            }

                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .height(50.dp)
                                    .background(Color.Transparent),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.White,
                                )
                            ) {

                                Text(
                                    text = "PUNCH ME OUT",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    PunchOutTheme {

        MainScreen()
    }
}
