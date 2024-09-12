package com.example.smartexpensetracker.onBoarding

import android.graphics.drawable.Icon
import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartexpensetracker.ui.theme.dimens

@Composable
fun Verification(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF03A9F4))
    ){
        LazyColumn {
            item{
                Title()
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))
            }
            item{
                VerifyCodeText()
            }
        }
    }
}


@Composable
fun VerifyCodeText(){
    Column(
        Modifier
            .padding(start = 30.dp, top = MaterialTheme.dimens.medium2)
    ) {
        Text(
            text = "Enter your Verification Code",
            fontSize = 37.sp,
            fontWeight = FontWeight.Bold
        )
    }
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
            text = "We sent you a verification code on your input Email Address. " +
                    "Please check your inbox for the code",
            fontSize = 20.sp
        )
    }
}

@Composable
fun VerifyEditText(){
    
}

@Composable
fun Title() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.dimens.small2,
                start = MaterialTheme.dimens.small1
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
        )

        Spacer(modifier = Modifier.width(107.dp))

        Text("Verification",
            fontSize = 24.sp)
    }
}


@Composable
@Preview
fun VerifyPrev(){
    Verification()
}