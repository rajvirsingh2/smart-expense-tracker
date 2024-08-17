package com.example.smartexpensetracker.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartexpensetracker.R
import com.example.smartexpensetracker.navigation.SignInNav
import com.example.smartexpensetracker.ui.theme.dimens


@Composable
fun SignUpScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF03A9F4))
            .padding(top = 20.dp)
    ){
        Greetings()
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraLarge))

        LazyColumn {
            item {
                SignUpEmail()
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraLarge))
                SignUpButton()
            }

            item {
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))
                Text(
                    text = "------------------------OR------------------------",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))
            }

            item {
                SignInNav()
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))
            }
            item {
                Login(navController = navController)
            }
        }
    }
}

@Composable
fun Greetings(){
    Column(
        modifier =
        Modifier
            .padding(start = 30.dp, top = MaterialTheme.dimens.medium2)
            .fillMaxWidth()
    ){
        Text(
            text = "Let's Get Started",
            fontSize = 20.sp
        )

        Text(
            text = "Create an Account",
            fontSize = 33.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpEmail(){

    val name = remember {
        mutableStateOf("")
    }

    val emailText = remember {
        mutableStateOf("")
    }

    val passwordText = remember {
        mutableStateOf("")
    }

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    val errorMessage = remember {
        mutableStateOf("")
    }

    val nameErrorMessage = remember {
        mutableStateOf("")
    }

    val emailErrorMessage = remember {
        mutableStateOf("")
    }

    //Name
    Column{
        val containerColor = Color.White
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.medium2),
            trailingIcon = {
                Icon(Icons.Filled.AccountCircle, contentDescription = "email")
            },
            value = name.value,
            onValueChange = {
                name.value = it
                nameErrorMessage.value = validateName(name.value)
            },
            label = {
                Text(
                    text = "Name",
                    fontSize = 18.sp
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor =  Color.DarkGray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
            )
        )

        if (nameErrorMessage.value.isNotEmpty()) {
            Text(
                text = nameErrorMessage.value,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = MaterialTheme.dimens.small1)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        //Email
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.medium2),
            trailingIcon = {
                Icon(Icons.Filled.Email, contentDescription = "email")
            },
            value = emailText.value,
            onValueChange = {
                emailText.value = it
                emailErrorMessage.value = validateEmail(it)
            },
            label = {
                Text(
                    text = "Email",
                    fontSize = 18.sp
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = containerColor,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.DarkGray,
                focusedLabelColor = Color.Black.copy(.9f),
                unfocusedLabelColor = Color.Gray
            )
        )

        if(emailErrorMessage.value.isNotEmpty()){
            Text(
                text = emailErrorMessage.value,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = MaterialTheme.dimens.small1)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        //Password
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.medium2),
            value = passwordText.value,
            onValueChange = {
                passwordText.value = it
                errorMessage.value = validatePassword(it)
            },
            label = {
                Text(
                    text = "Password",
                    fontSize = 18.sp
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = containerColor,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.DarkGray,
                focusedLabelColor = Color.Black.copy(.9f),
                unfocusedLabelColor = Color.Gray
            ),
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisibility.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = image,
                        contentDescription = "show password"
                    )
                }
            })
        if(errorMessage.value.isNotEmpty()){
            Text(
                text = errorMessage.value,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 40.dp)
            )
        }
    }
}

@Composable
fun SignUpButton(){
    ElevatedButton(onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimens.medium2)
            .height(MaterialTheme.dimens.buttonHeight),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )) {
        Text(text = "Continue")
    }
}

fun validateName(name: String): String {
    return if (name.isEmpty()) "Name is required" else ""
}

fun validateEmail(email: String): String {
    return if (email.isEmpty()) {
        "Email is required"
    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        "Invalid email address"
    } else {
        ""
    }
}

@Composable
fun Login(navController: NavController){
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.Center){
        Text(text = "Already have an account?",
            fontSize = 18.sp)

        Text(text = "Login Here",
            textDecoration = TextDecoration.Underline,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    navController.navigate("LoginScreen") {
                        popUpTo("SignUpScreen") {
                            inclusive = true
                        }
                    }
                }
        )
    }
}