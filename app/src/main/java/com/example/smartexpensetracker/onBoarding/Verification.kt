package com.example.smartexpensetracker.onBoarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartexpensetracker.ui.theme.dimens

@Composable
fun Verification(){

}

@Composable
fun VerifyGreeting() {
    Column(
        modifier =
        Modifier
            .padding(start = 30.dp, top = MaterialTheme.dimens.medium2)
            .fillMaxWidth(0.9f)
    ) {

        Text(
            text = "Verify Yourself",
            fontSize = 20.sp
        )

        Text(
            text = "We sent you a verification code on your input Email Address. Please check your inbox for the code",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun VerifyPrev(){
    Verification()
}