package com.secual_inc.punchout

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.secual_inc.punchout.ui.theme.PunchOutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {

    TopAppBar(
        title = {},
        navigationIcon = {

            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
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

        TopBar(scope, scaffoldState)
    }
}
