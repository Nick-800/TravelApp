package com.example.travelapp.ui.theme.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelapp.R
import com.example.travelapp.UserdataViewModel
import com.example.travelapp.ui.theme.routes.Routes
import com.example.travelapp.data.Users
import com.example.travelapp.data.Userdata
import com.example.travelapp.ui.theme.components.customFont


@Composable
fun SignUpPage(nav: NavController, userModel:UserdataViewModel = viewModel()) {
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("")}

    MaterialTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            Box{
                Image(painter = painterResource(R.drawable.desertsignup),
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
                SignUpHeader()
                SignUpInputFields(
                    email = email.value,
                    username = username.value,
                    password = password.value,
                    confirmPassword= confirmPassword.value,
                    onEmailChange = { email.value = it },
                    onUsernameChange = { username.value = it },
                    onPasswordChange = { password.value = it },
                    onConfirmPasswordChange ={confirmPassword.value = it}

                )
                SignUpButton(
                    onClick = {
                        userModel.userdata.add(Userdata(id = 0, name = username.value,
                            secondName = "Kamash", email = "blah@gmail.com",
                            location = "Benghazi", phone = "0927441569"))
                        nav.navigate(Routes.Login)
                    }
                )
                AlreadyHaveAccount(nav)
            }
        }
    }
}

@Composable
fun SignUpHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Create Account", style = TextStyle(
            fontFamily = customFont,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 42.sp,
            color = Color.Black
        ))

        Text(text = "Sign up to get started", style = TextStyle(
            fontFamily = customFont,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        ))
    }
}

@Composable
fun SignUpInputFields(
    email: String,
    username: String,
    password: String,
    confirmPassword: String,
    onEmailChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth(1f).padding(16.dp),
            horizontalArrangement = Arrangement.Start){

        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text(text = "AAA", style = TextStyle(
                fontFamily = customFont,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username Icon") },
            modifier = Modifier.fillMaxWidth(0.5f).padding(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1A73E8),
                unfocusedBorderColor = Color(0xFF7A7A7A),
                unfocusedLabelColor = Color(0xFF7A7A7A),
                focusedLabelColor = Color(0xFF1A73E8),
                unfocusedLeadingIconColor = Color(0xFF7A7A7A),
                focusedLeadingIconColor = Color(0xFF1A73E8),
                focusedTextColor = Color.Black,


            ),
            shape = MaterialTheme.shapes.medium
        )

            OutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                label = { Text(text = "AAAA", style = TextStyle(
                    fontFamily = customFont,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username Icon") },
                modifier = Modifier.fillMaxWidth(1f).padding(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1A73E8),
                    unfocusedBorderColor = Color(0xFF7A7A7A),
                    unfocusedLabelColor = Color(0xFF7A7A7A),
                    focusedLabelColor = Color(0xFF1A73E8),
                    unfocusedLeadingIconColor = Color(0xFF7A7A7A),
                    focusedLeadingIconColor = Color(0xFF1A73E8),
                    focusedTextColor = Color.Black,


                    ),
                shape = MaterialTheme.shapes.medium
            )

        }
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text(text = "Email", style = TextStyle(
                fontFamily = customFont,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = "Password", style = TextStyle(
                fontFamily = customFont,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )) },
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

        val confirmPasswordError = remember { mutableStateOf(false) }
        val confirmPasswordErrorMessage = remember { mutableStateOf("") }

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { newValue ->
                onConfirmPasswordChange(newValue)
                if (newValue != password) {
                    confirmPasswordError.value = true
                    confirmPasswordErrorMessage.value = "Passwords do not match"
                } else {
                    confirmPasswordError.value = false
                    confirmPasswordErrorMessage.value = ""
                }
            },
            label = { Text(text = "Confirm Password", style = TextStyle(
                fontFamily = customFont,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Confirm Password Icon") },
            modifier = Modifier.fillMaxWidth(),
            colors = if (confirmPasswordError.value) {
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Red,
                    unfocusedBorderColor = Color.Red,
                    unfocusedLabelColor = Color.Red,
                    focusedLabelColor = Color.Red,
                    unfocusedLeadingIconColor = Color.Red,
                    focusedLeadingIconColor = Color.Red,
                    focusedTextColor = Color.Red
                )
            } else {
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1A73E8),
                    unfocusedBorderColor = Color(0xFF7A7A7A),
                    unfocusedLabelColor = Color(0xFF7A7A7A),
                    focusedLabelColor = Color(0xFF1A73E8),
                    unfocusedLeadingIconColor = Color(0xFF7A7A7A),
                    focusedLeadingIconColor = Color(0xFF1A73E8),
                    focusedTextColor = Color.Black
                )
            },
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (confirmPasswordError.value) {
            Text(
                text = confirmPasswordErrorMessage.value,
                style = TextStyle(
                    fontFamily = customFont,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Red
                ),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

    }
}

@Composable
fun SignUpButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8EC3CC)),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        Text(text = "Sign up", style = TextStyle(
            fontFamily = customFont,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        ))
    }
}

@Composable
fun AlreadyHaveAccount(nav: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Already have an account?", style = TextStyle(
            fontFamily = customFont,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        ))
        TextButton(onClick = { nav.navigate(Routes.Login) }) {

            Text(text = "Log in", style = TextStyle(
                fontFamily = customFont,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF32839B)
            ))


        }
    }
}
