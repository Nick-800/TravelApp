package com.example.travelapp


import HomeScreen
import SettingsContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.travelapp.data.Users
import com.example.travelapp.ui.theme.ToDoAppTheme
import com.example.travelapp.ui.theme.login.LoginPage
import com.example.travelapp.ui.theme.login.SignUpPage
import com.example.travelapp.ui.theme.login.StartUpPage
import com.example.travelapp.ui.theme.routes.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
                val users = remember { mutableStateListOf<Users>() }


                val navController = rememberNavController()


                NavHost(navController = navController, startDestination = Routes.StartUp) {
                    composable(Routes.StartUp) {
                        StartUpPage(nav = navController)
                    }
                    composable(Routes.Login) {
                        LoginPage(nav = navController, users = users)
                    }
                    composable(Routes.Signup) {
                        SignUpPage(nav = navController, users = users)
                    }

                    composable("intro/{username}/{lastname}/{avatarResource}",
                        arguments = listOf(
                            navArgument("username") { defaultValue = "Guest" },
                            navArgument("lastname") { defaultValue = "User" },
                            navArgument("avatarResource") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val username = backStackEntry.arguments?.getString("username") ?: "Guest"
                        val lastname = backStackEntry.arguments?.getString("lastname") ?: "User"
                        val avatarResource = backStackEntry.arguments?.getInt("avatarResource") ?: R.drawable.cultural_sites
                        IntroScreen(navController, "$username $lastname", avatarResource)
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





