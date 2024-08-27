package com.example.todoapp.ui.theme.login

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.routes.Routes
import com.example.todoapp.data.Users

@Composable
fun LoginPage(nav: NavController, users: List<Users>) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val showErrorDialog = remember { mutableStateOf(false) }
    val forgotPasswordDialog = remember { mutableStateOf(false) }

    MaterialTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0)),
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(text = "Login", fontSize = 20.sp)
                }

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
fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Welcome Back",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Enter your credentials to login")
    }
}

@Composable
fun InputField(email: String, password: String, onEmailChange: (String) -> Unit, onPasswordChange: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Email Icon") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF9C27B0).copy(alpha = 0.1f)),
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF9C27B0).copy(alpha = 0.1f)),
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }
}

@Composable
fun ForgotPassword(forgotPasswordDialog: MutableState<Boolean>) {
    TextButton(onClick = {
        forgotPasswordDialog.value = true
    }) {
        Text(text = "Forgot password?", color = Color(0xFF9C27B0))
    }
}

@Composable
fun SignUp(nav: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Don't have an account?")
        TextButton(onClick = {
            nav.navigate(Routes.Signup)
        }) {
            Text(text = "Sign Up", color = Color(0xFF9C27B0))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage(nav = rememberNavController(), users = listOf())
}
