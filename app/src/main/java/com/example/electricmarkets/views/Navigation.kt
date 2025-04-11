package com.example.electricmarkets.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.electricmarkets.views.LoginScreen
import com.example.electricmarkets.views.SignUpScreen
import com.example.electricmarkets.views.HomeScreen
import com.example.electricmarkets.viewmodel.AuthViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                navController = navController
            )
        }

        composable("signup") {
            SignUpScreen(
                authViewModel = authViewModel,  // Passing the authViewModel
                navController = navController,  // Passing the navController
                onSignUpClick = { email, password ->
                    authViewModel.register(email, password) {
                        navController.popBackStack()  // Go back to the login screen after successful registration
                    }
                },
                onLoginClick = {
                    navController.popBackStack()  // Go back to the login screen when the user has an account
                }
            )
        }

        composable("home") {
            HomeScreen()  // Home screen after login
        }
    }
}
