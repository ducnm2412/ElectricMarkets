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
import com.example.electricmarkets.views.AboutScreen
import com.example.electricmarkets.views.CartScreen
import com.example.electricmarkets.views.FeedBackScreen
import com.example.electricmarkets.views.MenuScreen
import com.example.electricmarkets.views.OrderDetailsScreen
import com.example.electricmarkets.views.OrderHistoryScreen
import com.example.electricmarkets.views.ProductDetailScreen
import com.example.electricmarkets.views.ProfileScreen
import com.example.electricmarkets.views.SaleScreen
import viewmodel.ContactViewModel
import viewmodel.OrderViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val contactViewModel: ContactViewModel = viewModel()
    val orderViewModel: OrderViewModel = viewModel()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                navController = navController
            )
        }

        composable("signup") {
            SignUpScreen(
                authViewModel = authViewModel,
                navController = navController,
                onSignUpClick = { email, password, fullName, phone, address ->
                    authViewModel.register(email, password, fullName, phone, address) {
                        navController.popBackStack()
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("sale") {
            SaleScreen(navController = navController)
        }

        composable("cart") {
            CartScreen(navController = navController)
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }

        composable("menu") {
            MenuScreen(navController = navController)
        }

        composable("feedback") {
            FeedBackScreen(navController = navController,contactViewModel = contactViewModel)
        }

        composable("about") {
            AboutScreen(navController = navController)
        }
        composable("orderDetailsScreen") {
            OrderDetailsScreen(navController = navController,orderViewModel = OrderViewModel())
        }
        composable("historyOrderScreen") {
            OrderHistoryScreen(navController = navController,orderViewModel = OrderViewModel())
        }
        composable("productDetailScreen/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(navController = navController, productId = productId)
        }

    }
}

