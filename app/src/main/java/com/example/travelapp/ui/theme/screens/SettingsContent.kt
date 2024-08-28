import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsContent(nav: NavController, email: String, password: String, username: String) {
    var newEmail by remember { mutableStateOf(email) }
    var newPassword by remember { mutableStateOf(password) }
    var newUsername by remember { mutableStateOf(username) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF891E9C),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = newEmail,
            onValueChange = { newEmail = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color(0xFF673AB7),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF673AB7)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newUsername,
            onValueChange = { newUsername = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color(0xFF673AB7),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF673AB7)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color(0xFF673AB7),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF673AB7)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
//                email=newEmail,
//                password=newPassword,
//                username=newUsername
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF512DA8))
        ) {
            Text(text = "Save Changes", fontSize = 18.sp, color = Color.White)
        }
    }
}
