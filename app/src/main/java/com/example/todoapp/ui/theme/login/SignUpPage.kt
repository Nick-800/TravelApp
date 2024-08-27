import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.theme.routes.Routes
import com.example.todoapp.data.Users

@Composable
fun SignUpPage(nav: NavController, users: MutableList<Users>) {
    val email = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

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
                SignUpHeader()
                SignUpInputFields(
                    email = email.value,
                    username = username.value,
                    password = password.value,
                    onEmailChange = { email.value = it },
                    onUsernameChange = { username.value = it },
                    onPasswordChange = { password.value = it }
                )
                SignUpButton(
                    onClick = {
                        users.add(Users(email.value, username.value, password.value))
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
        Text(
            text = "Create Account",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Sign up to get started")
    }
}

@Composable
fun SignUpInputFields(
    email: String,
    username: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Username Icon") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF9C27B0).copy(alpha = 0.1f)),
            shape = MaterialTheme.shapes.medium
        )
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
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
fun SignUpButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9C27B0)),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        Text(text = "Sign Up", fontSize = 20.sp)
    }
}

@Composable
fun AlreadyHaveAccount(nav: NavController) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Already have an account?")
        TextButton(onClick = { nav.navigate(Routes.Login) }) {
            Text(text = "Log In", color = Color(0xFF9C27B0))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview() {
    val users = mutableListOf<Users>()
    SignUpPage(nav = rememberNavController(), users = users)
}
