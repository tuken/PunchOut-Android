package com.secual_inc.punchout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.secual_inc.punchout.ui.theme.PunchOutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(scope: CoroutineScope, sheetState: ModalBottomSheetState) {

    val showInAlert = remember { mutableStateOf(false) }

    val showOutAlert = remember { mutableStateOf(false) }

    Column {

        CalendarScreen()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                onClick = {
                    showInAlert.value = true
                },
                modifier = Modifier
                    .width(150.dp)
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
                onClick = {
//                    showOutAlert.value = true
                    scope.launch {
                        sheetState.show()
                    }
                },
                modifier = Modifier
                    .width(150.dp)
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

    if (showInAlert.value) {

        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(text = "出勤処理")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showInAlert.value = false
                    }
                ) {
                    Text("OK")
                }
            },
        )
    }

    if (showOutAlert.value) {

        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(text = "退勤処理")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showOutAlert.value = false
                    }
                ) {
                    Text("OK")
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun HomePreview() {

    PunchOutTheme {

        val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

        val scope = rememberCoroutineScope()

        HomeScreen(scope = scope, sheetState = modalBottomSheetState)
    }
}
