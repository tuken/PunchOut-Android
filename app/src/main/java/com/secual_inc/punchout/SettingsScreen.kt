package com.secual_inc.punchout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.secual_inc.punchout.ui.theme.PunchOutTheme

@Composable
fun SettingsScreen() {

    Box(
        modifier = Modifier.background(Color(red = 1, green = 172, blue = 200))
            .fillMaxSize()
    ) {

        Text(text = "Settings Screen")
    }

}

@Preview
@Composable
fun SettingsPreview() {

    PunchOutTheme {

        SettingsScreen()
    }
}
