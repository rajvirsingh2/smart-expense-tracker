package com.example.smartexpensetracker.onBoarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp

@Composable
fun IndicatorRow(currentPage: Int) {
    Row(modifier = Modifier
        .background(Color(0xFF03A9F4))
        .fillMaxWidth()
        .padding(bottom = 60.dp),
        horizontalArrangement = Arrangement.Center,) {
        val colors = listOf(
            animateColorAsState(targetValue = if (currentPage == 0) Color.Red else Color.LightGray, animationSpec = tween(durationMillis = 300)),
            animateColorAsState(targetValue = if (currentPage == 1) Color.Red else Color.LightGray, animationSpec = tween(durationMillis = 300)),
            animateColorAsState(targetValue = if (currentPage == 2) Color.Red else Color.LightGray, animationSpec = tween(durationMillis = 300))
        )
        colors.forEachIndexed { index, colorState ->
            Canvas(modifier = Modifier.size(10.dp)) {
                drawCircle(
                    color = colorState.value,
                    radius = size.minDimension / 2,
                    style = Fill
                )
            }
            if (index < colors.size - 1) {
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}
