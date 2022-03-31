package com.secual_inc.punchout

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItemDrawer(route: String, title: String, icon: Int, onItemClick: (String) -> Unit) {

//    val background = if (selected) R.color.teal_200 else android.R.color.transparent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(route) })
            .height(45.dp)
            .background(colorResource(id = R.color.transparent))
            .padding(start = 10.dp)
    ) {

        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(end = 10.dp)
//                .height(35.dp)
//                .width(35.dp)
        )

        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}
