package com.example.travelapp.ui.theme.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.travelapp.R
import com.example.travelapp.ui.theme.routes.Routes
import com.example.travelapp.data.Users
import com.example.travelapp.ui.theme.components.customFont

@Composable
fun LoginPage(nav: NavController, users: List<Users>) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val showErrorDialog = remember { mutableStateOf(false) }
    val forgotPasswordDialog = remember { mutableStateOf(false) }

    MaterialTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            Box{
                Image(painter = painterResource(R.drawable.desertlogin),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(0.45f)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Header()
                InputField(
                    email.value,
                    password.value,
                    onEmailChange = { email.value = it },
                    onPasswordChange = { password.value = it }
                )
                Button(
                    onClick = {
                        val userExists = users.firstOrNull { it.email == email.value && it.password == password.value }
                        if (userExists != null) {
                            nav.navigate("home/${userExists.email}/${userExists.password}/${userExists.username}")
                        } else {
                            showErrorDialog.value = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8EC3CC)),
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        text = "Login",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = customFont,
                            fontWeight = FontWeight.Normal,
                            fontStyle = FontStyle.Normal,
                            color = Color.White
                        )
                    ) }

                ForgotPassword(forgotPasswordDialog)
                SignUp(nav)
            }
        }
    }

    if (showErrorDialog.value) {
        AlertDialog(
            onDismissRequest = { showErrorDialog.value = false },
            title = { Text("Login Error") },
            text = { Text("The email or password you entered is incorrect.") },
            confirmButton = {
                TextButton(
                    onClick = { showErrorDialog.value = false }
                ) {
                    Text("OK")
                }
            }
        )
    }

    if (forgotPasswordDialog.value) {
        AlertDialog(
            onDismissRequest = { forgotPasswordDialog.value = false },
            title = { Text("Forgot Password?") },
            text = { Text("I don't think you forgot your password.\nI think you don't have one, you idiot.") },
            confirmButton = {
                TextButton(
                    onClick = { forgotPasswordDialog.value = false }
                ) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
private fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Welcome Back",
            style = TextStyle(
                fontSize = 42.sp,
                fontFamily = customFont,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = Color.White
            )
        )


        Text(
            text = "Enter your credentials to login",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = customFont,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
        )
    }
}

@Composable
private fun InputField(email: String, password: String, onEmailChange: (String) -> Unit, onPasswordChange: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = {
                Text(text = "Email",
                    style = TextStyle(
                    fontFamily = customFont,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ))
                //CustomText("Email", modifier = Modifier.fillMaxHeight(0.5f))

                    },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Email Icon") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1A73E8),
                unfocusedBorderColor = Color(0xFF6D6D6D),
                unfocusedLabelColor = Color(0xFF6D6D6D),
                focusedLabelColor = Color(0xFF1A73E8),
                unfocusedLeadingIconColor = Color(0xFF6D6D6D),
                focusedLeadingIconColor = Color(0xFF1A73E8),
                focusedTextColor = Color.Black

            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {
                Text(text = "Password",
                    style = TextStyle(
                    fontFamily = customFont,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ))
//                CustomText("Password", fontWeight= FontWeight.Bold,
//                modifier = Modifier.fillMaxHeight(0.5f)
             },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1A73E8),
                unfocusedBorderColor = Color(0xFF7A7A7A),
                unfocusedLabelColor = Color(0xFF7A7A7A),
                focusedLabelColor = Color(0xFF1A73E8),
                unfocusedLeadingIconColor = Color(0xFF7A7A7A),
                focusedLeadingIconColor = Color(0xFF1A73E8),
                focusedTextColor = Color.Black

            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

@Composable
private fun ForgotPassword(forgotPasswordDialog: MutableState<Boolean>) {
    TextButton(onClick = {
        forgotPasswordDialog.value = true
    }) {
        Text(text = "Forgot password", style = TextStyle(
            fontFamily = customFont,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color(0xFFAC2C2C)
        ))

//        CustomText(
//            text = "Forgot password?",
//            fontStyle = FontStyle.Italic,
//            color = Color(0xFFAC2C2C),
//            modifier = Modifier.fillMaxHeight(0.5f)
//        )
    }
}

@Composable
private fun SignUp(nav: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Don't have an account?",
            style = TextStyle(
            fontFamily = customFont,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        ))
//        CustomText(
//            text = "Don't have an account?",
//            color = Color.White,
//            modifier = Modifier.fillMaxHeight(0.5f)
//        )

        TextButton(onClick = {
            nav.navigate(Routes.Signup)
        }) {

            Text(text = "Sign up", style = TextStyle(
                fontFamily = customFont,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF32839B)
            ))
//            CustomText(
//                text = "Sign Up",
//                fontStyle = FontStyle.Italic,
//                color = Color(0xFF32839B),
//                modifier = Modifier.fillMaxHeight(0.5f)
//            )
        }
    }
}

