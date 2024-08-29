package com.example.travelapp.ui.theme.login


import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travelapp.R
import com.example.travelapp.UserdataViewModel
import com.example.travelapp.ui.theme.routes.Routes
import com.example.travelapp.data.Userdata
import com.example.travelapp.ui.theme.components.customFont



@Composable
fun SignUpPage(nav: NavController, userModel:UserdataViewModel) {
    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val secondname = remember { mutableStateOf("") }
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
                    name = name.value,
                    secondname=secondname.value,
                    password = password.value,
                    confirmPassword= confirmPassword.value,
                    onEmailChange = { email.value = it },
                    onNameChange = { name.value = it },
                    onSecondNameChange = {secondname.value = it },
                    onPasswordChange = { password.value = it },
                    onConfirmPasswordChange ={confirmPassword.value = it}

                )
                SignUpButton(
                    onClick = {
                        userModel.addUser(Userdata(email = email.value, name = name.value, password = password.value))
                        nav.navigate(Routes.Login)
                    }, context = LocalContext.current
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
    name: String,
    secondname:String,
    password: String,
    confirmPassword: String,
    onEmailChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onSecondNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween){

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = "First Name", style = TextStyle(
                fontFamily = customFont,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )) },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username Icon") },
            modifier = Modifier
                .fillMaxWidth(0.5f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1A73E8),
                unfocusedBorderColor = Color(0xFF7A7A7A),
                unfocusedLabelColor = Color(0xFF7A7A7A),
                focusedLabelColor = Color(0xFF1A73E8),
                unfocusedLeadingIconColor = Color(0xFF7A7A7A),
                focusedLeadingIconColor = Color(0xFF1A73E8),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,


            ),
            shape = MaterialTheme.shapes.medium
        )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = secondname,
                onValueChange = onSecondNameChange,
                label = { Text(text = "Last Name", style = TextStyle(
                    fontFamily = customFont,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username Icon") },
                modifier = Modifier
                    .fillMaxWidth(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1A73E8),
                    unfocusedBorderColor = Color(0xFF7A7A7A),
                    unfocusedLabelColor = Color(0xFF7A7A7A),
                    focusedLabelColor = Color(0xFF1A73E8),
                    unfocusedLeadingIconColor = Color(0xFF7A7A7A),
                    focusedLeadingIconColor = Color(0xFF1A73E8),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,


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
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black

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
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black

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
                    focusedTextColor = Color.Red,
                    unfocusedTextColor = Color.Red
                )
            } else {
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1A73E8),
                    unfocusedBorderColor = Color(0xFF7A7A7A),
                    unfocusedLabelColor = Color(0xFF7A7A7A),
                    focusedLabelColor = Color(0xFF1A73E8),
                    unfocusedLeadingIconColor = Color(0xFF7A7A7A),
                    focusedLeadingIconColor = Color(0xFF1A73E8),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
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
fun SignUpButton(onClick: () -> Unit, context: Context) {
    Button(
        onClick ={ onClick()}
        ,
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


