package com.example.smartexpensetracker.onBoarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartexpensetracker.R


@Composable
fun OnBoarding3(modifier: Modifier = Modifier) {
    val acmeFont = FontFamily(Font(R.font.acme_regular, FontWeight.Normal))
    val oswaldFont = FontFamily(Font(R.font.oswald_regular))

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF03A9F4))
            .padding(top = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(painter = painterResource(id = R.drawable.money3), contentDescription = null)
        }
        item {
            Spacer(modifier = Modifier.padding(top = 18.dp))
            Text(
                text = "Plan Your Expenses",
                fontFamily = acmeFont,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 50.dp, start = 20.dp, end = 20.dp)
            )
            Spacer(modifier = Modifier.padding(top = 33.dp))
            Text(
                text = "Spend According to the Budget",
                fontFamily = oswaldFont,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
            Spacer(modifier = Modifier.padding(top = 40.dp))
        }
    }
}
