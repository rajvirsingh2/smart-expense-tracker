package com.example.smartexpensetracker.onBoarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraLarge))
            }
            item {
                VerifyEditText()
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))
            }
            item{
                VerifyConfirm()
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.large))
            }
            item {
                VerifyButton()
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
fun VerifyConfirm() {
    Column(
        modifier =
        Modifier
            .padding(start = 30.dp, top = MaterialTheme.dimens.medium2)
            .fillMaxWidth(0.9f)
    ) {
        Text(
            text = "We send a verification code to your email." +
                    " You can check your inbox",
            fontSize = 25.sp
        )
    }
}

@SuppressLint("AutoboxingStateValueProperty")
@Composable
fun VerifyEditText() {
    val clickedIndex = remember { mutableStateOf(-1) } // -1 means no circle is clicked
    val inputValues = remember { mutableStateOf(List(6) { "" }) } // List of input values for each circle
    val focusRequester = remember { FocusRequester() }

    Row(modifier = Modifier
        .padding(start = MaterialTheme.dimens.medium2)) {
        for (i in 0 until 6) {
            if (clickedIndex.value == i) {
                // Text Field shown when circle is clicked
                OutlinedTextField(
                    value = inputValues.value[i],
                    onValueChange = { newValue ->
                        // Update input value
                        val updatedValues = inputValues.value.toMutableList()
                        updatedValues[i] = newValue
                        inputValues.value = updatedValues

                        // Move focus to the next field if input length is 1
                        if (newValue.length == 1) {
                            if (i < 5) {
                                clickedIndex.value = i + 1
                            } else {
                                clickedIndex.value = -1 // Deselect last field
                            }
                        }
                    },
                    label = { Text("") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .size(24.dp)
                        .focusRequester(focusRequester) // Set the focus to this text field
                )

                // Request focus after the field is displayed
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            } else {
                // Display entered value or circle based on whether the value is empty
                if (inputValues.value[i].isEmpty()) {
                    // Draw circle if no value is entered
                    Canvas(modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            clickedIndex.value = i // Update clicked index and trigger recomposition
                        }) {
                        drawCircle(
                            color = Color.Gray,
                            radius = size.minDimension / 2,
                            style = Fill
                        )
                    }
                } else {
                    // Display the entered number if available
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { clickedIndex.value = i },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = inputValues.value[i],
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 25.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.medium2)) // Adjust padding between elements as needed
        }
    }
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
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
fun VerifyButton(){
    Column {
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.dimens.medium2, end = MaterialTheme.dimens.medium2)
                .height(MaterialTheme.dimens.buttonHeight),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ))
        {
            Text(text = "Verify")
        }
    }
}

@Composable
@Preview
fun VerifyPrev(){
    Verification()
}