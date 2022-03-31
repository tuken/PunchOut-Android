package com.secual_inc.punchout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.secual_inc.punchout.ui.theme.PunchOutTheme

@Composable
fun HomeScreen() {

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

@Preview(showBackground = true)
@Composable
fun HomePreview() {

    PunchOutTheme {

        HomeScreen()
    }
}
