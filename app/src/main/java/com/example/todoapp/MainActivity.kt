package com.example.todoapp


import HomeScreen
import SettingsContent
import SignUpPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.data.Users
import com.example.todoapp.screens.ProfileContent
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.example.todoapp.ui.theme.login.LoginPage
import com.example.todoapp.ui.theme.routes.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                val users = remember { mutableStateListOf<Users>() }


                val navController = rememberNavController()


                NavHost(navController = navController, startDestination = Routes.Login) {
                    composable(Routes.Login) {
                        LoginPage(nav = navController, users = users)
                    }
                    composable(Routes.Signup) {
                        SignUpPage(nav = navController, users = users)
                    }
                    composable(Routes.Home) { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email") ?: ""
                        val password = backStackEntry.arguments?.getString("password") ?: ""
                        val username = backStackEntry.arguments?.getString("username") ?: ""
                        HomeScreen(nav = navController, email = email, password = password, username = username)
                    }
//                    composable(Routes.Profile) { backStackEntry ->
//                    val viewModel = backStackEntry.arguments?.
//                    val pa = backStackEntry.arguments?.getString("p") ?: ""
//                        ProfileContent(nav = navController, viewModel = viewModel, p = p)
//                    }
                    composable(Routes.Settings) { backStackEntry ->
                        val email = backStackEntry.arguments?.getString("email") ?: ""
                        val password = backStackEntry.arguments?.getString("password") ?: ""
                        val username = backStackEntry.arguments?.getString("username") ?: ""
                        SettingsContent(nav = navController, email = email, password = password, username = username)
                    }
                }

            }

            }
    }
}




