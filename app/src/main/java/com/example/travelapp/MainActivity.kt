package com.example.travelapp



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.travelapp.ui.theme.screens.AdminScreen
import com.example.travelapp.ui.theme.screens.HomeScreen
import com.example.travelapp.ui.theme.screens.SplashScreen
import com.example.travelapp.ui.theme.screens.TravelDetailsScreen
import com.example.travelapp.ui.theme.screens.Travelcard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoAppTheme {
val userModel:UserdataViewModel = viewModel()

                val navController = rememberNavController()




                NavHost(navController = navController, startDestination = Routes.Splash) {
                    composable(Routes.Splash){
                        SplashScreen(navController = navController)
                    }
                    composable(Routes.StartUp) {
                        StartUpPage(nav = navController)
                    }
                    composable(Routes.Login) {
                        LoginPage(nav = navController, userModel= userModel)
                    }
                    composable(Routes.Signup) {
                        SignUpPage(nav = navController, userModel= userModel)
                    }
                    composable(Routes.Home){
                        HomeScreen(username = "", email = "", password = "")
                    }
                    composable(Routes.Admin){
                        AdminScreen()
                    }
                    composable(Routes.Travel){
                        TravelDetailsScreen(travelcard = Travelcard(0,"","",0.0,""))
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

//                    composable(Routes.Profile) { backStackEntry ->
//                    val viewModel = backStackEntry.arguments?.
//                    val pa = backStackEntry.arguments?.getString("p") ?: ""
//                        ProfileContent(nav = navController, viewModel = viewModel, p = p)
//                    }
                }

            }

            }
    }
}




