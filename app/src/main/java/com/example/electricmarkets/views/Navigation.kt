package com.example.electricmarkets.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.electricmarkets.views.LoginScreen
import com.example.electricmarkets.views.SignUpScreen
import com.example.electricmarkets.views.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginClick = { email, password ->
                    navController.navigate("home") {
                        // Avoid going back to the login screen after navigating to home
                        popUpTo("login") { inclusive = true }
                    }
                },
                onSignUpClick = {
                    navController.navigate("signup")
                }
            )
        }
        composable("signup") {
            SignUpScreen(
                onSignUpClick = { email, password ->
                    navController.popBackStack() // Go back to login screen after signup
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("home") {
            HomeScreen()
        }
    }
}
