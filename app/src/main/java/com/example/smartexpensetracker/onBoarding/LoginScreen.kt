package com.example.smartexpensetracker.onBoarding

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smartexpensetracker.R
import com.example.smartexpensetracker.navigation.GoogleSignInNav
import com.example.smartexpensetracker.security.GoogleSignInState
import com.example.smartexpensetracker.ui.theme.dimens
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

@Composable
fun LoginScreen(navController: NavController) {

    val emailText = rememberSaveable { mutableStateOf("") }
    val passwordText = rememberSaveable { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF03A9F4))
            .padding(top = 20.dp)
    ) {
        // Non-scrollable content above the list
        Greeting()
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraLarge))

        // Scrollable list
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                Email(emailText, passwordText, errorMessage)
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))
                ForgotPassword()
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))
                ContinueButton(emailText.value, passwordText.value)
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
                CreateAccount(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(){
    Column(
        modifier =
        Modifier
            .padding(start = 30.dp, top = MaterialTheme.dimens.medium2)
            .fillMaxWidth(0.9f)
    ) {

        Text(
            text = "WELCOME BACK",
            fontSize = 20.sp
        )

        Text(
            text = "Login to your Account",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(emailText: MutableState<String>,
          passwordText: MutableState<String>,
          errorMessage: MutableState<String>) {

    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }

    Column(Modifier.fillMaxSize()) {
        // Email
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.dimens.medium2, end = MaterialTheme.dimens.medium2),
            trailingIcon = {
                Icon(Icons.Filled.Email, contentDescription = "email")
            },
            value = emailText.value,
            onValueChange = {
                emailText.value = it
            },
            label = {
                Text(
                    text = "Email",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(horizontal = 4.dp)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedPlaceholderColor = Color.Black,
                unfocusedPlaceholderColor = Color.DarkGray,
                focusedLabelColor = Color.Black.copy(.9f),
                unfocusedLabelColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Password
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MaterialTheme.dimens.medium2, end = MaterialTheme.dimens.medium2),
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
                focusedContainerColor = Color.White,
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
}

@Composable
fun ContinueButton(email: String, password: String){

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    Button(onClick = {
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "Login Failed; ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
        }
    },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MaterialTheme.dimens.medium2, end = MaterialTheme.dimens.medium2)
            .height(MaterialTheme.dimens.buttonHeight),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )) {
        Text(text = "Login")
    }

}

@Composable
fun ForgotPassword(){
    Column{
        Text(
            text = "Forgot Password?",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 210.dp)
                .clickable { /*TODO*/ }
        )
    }
}

@Composable
fun GoogleSignIn(
    state: GoogleSignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth() // Take full width for the outer box
            .padding(horizontal = MaterialTheme.dimens.medium2) // Apply padding to the left and right sides
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(MaterialTheme.dimens.buttonHeight)
                .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                .background(Color.White, RoundedCornerShape(8.dp))
                .clickable { onSignInClick() }
                .padding(
                    top = 10.dp,
                    start = MaterialTheme.dimens.medium2,
                    end = MaterialTheme.dimens.medium2,
                    bottom = 10.dp
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google), // Replace with your Google icon resource
                    contentDescription = "Google",
                    modifier = Modifier.size(26.dp)
                )

                Spacer(modifier = Modifier.width(MaterialTheme.dimens.medium2))
            }

            Text(
                text = "Continue with Google",
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))
        }
    }
}

@Composable
fun CreateAccount(navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Text(text = "Don't have an account?",
            fontSize = 18.sp)
        Text(text = "Sign Up",
            textDecoration = TextDecoration.Underline,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate("SignUpScreen") {
                    popUpTo("LoginScreen") { inclusive = true }
                }
            }
        )
    }
}

fun validatePassword(password: String): String{
    val uppercasePattern = Pattern.compile(".*[A-Z].*")
    val digitPattern = Pattern.compile(".*\\d.*")
    val specialCharPattern = Pattern.compile(".*[!@#$%^&*()].*")

    return when{
        password.length < 8 -> "Password must be at least 8 characters long."
        !uppercasePattern.matcher(password).matches() -> "Password must contain at least one uppercase letter."
        !digitPattern.matcher(password).matches() -> "Password must contain at least one digit."
        !specialCharPattern.matcher(password).matches() -> "Password must contain at least one special character."
        else -> ""
    }
}

