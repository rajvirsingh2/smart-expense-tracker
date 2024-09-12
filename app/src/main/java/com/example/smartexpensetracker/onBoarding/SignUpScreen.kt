package com.example.smartexpensetracker.onBoarding

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartexpensetracker.navigation.GoogleSignInNav
import com.example.smartexpensetracker.ui.theme.dimens
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SignUpScreen(navController: NavController) {
    val name = rememberSaveable { mutableStateOf("") }
    val emailText = rememberSaveable { mutableStateOf("") }
    val passwordText = rememberSaveable { mutableStateOf("") }
    val nameErrorMessage = remember { mutableStateOf("") }
    val emailErrorMessage = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF03A9F4))
            .padding(top = 20.dp)
    ) {
        Greetings()
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraLarge))

        LazyColumn {
            item {
                SignUpEmail(
                    name = name,
                    emailText = emailText,
                    passwordText = passwordText,
                    nameErrorMessage = nameErrorMessage,
                    emailErrorMessage = emailErrorMessage,
                    errorMessage = errorMessage
                )
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
                GoogleSignInNav()
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
fun SignUpEmail(
    name: MutableState<String>,
    emailText: MutableState<String>,
    passwordText: MutableState<String>,
    nameErrorMessage: MutableState<String>,
    emailErrorMessage: MutableState<String>,
    errorMessage: MutableState<String>
) {
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }

    Column {
        // Name
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.medium2),
            trailingIcon = {
                Icon(Icons.Filled.AccountCircle, contentDescription = "name")
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
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.DarkGray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray
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

        // Email
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
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.DarkGray,
                focusedLabelColor = Color.Black.copy(.9f),
                unfocusedLabelColor = Color.Gray
            )
        )

        if (emailErrorMessage.value.isNotEmpty()) {
            Text(
                text = emailErrorMessage.value,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = MaterialTheme.dimens.small1)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Password
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
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
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
            }
        )

        if (errorMessage.value.isNotEmpty()) {
            Text(
                text = errorMessage.value,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 40.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraLarge))
    SignUpButton(name.value, emailText.value, passwordText.value)
}


@Composable
fun SignUpButton(
    name: String,
    email: String,
    password: String
) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    ElevatedButton(
        onClick = {
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(context as Activity) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Registration successful!", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimens.medium2)
            .height(MaterialTheme.dimens.buttonHeight),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {
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