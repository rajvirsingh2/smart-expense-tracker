package com.example.smartexpensetracker.onBoarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartexpensetracker.R
import com.example.smartexpensetracker.ui.theme.dimens

@Composable
fun SignIn(){

    val acmeFont = FontFamily(
        Font(R.font.acme_regular, FontWeight.Normal)
    )

    val oswaldFont = FontFamily(
        Font(R.font.oswald_regular)
    )

    val comfortaFamily = FontFamily(
        Font(R.font.comforta_bold, FontWeight.Bold),
        Font(R.font.comforta_light, FontWeight.Light),
        Font(R.font.comforta_regular, FontWeight.Normal),
    )

    val robotoFamily = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_medium, FontWeight.Medium)
    )

    Column(
        Modifier
            .background(Color(0xFF03A9F4))
            .padding(bottom = 40.dp, start = 30.dp, end = 30.dp)) {

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.buttonHeight),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )) {
            Text(text = "Sign Up",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = comfortaFamily)
        }

        Spacer(modifier = Modifier.padding(top = 25.dp))

        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.buttonHeight),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            )) {
            Text(text = "Login",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = comfortaFamily
            )
        }
    }
}

@Composable
@Preview
fun Prev(){
    SignIn()
}