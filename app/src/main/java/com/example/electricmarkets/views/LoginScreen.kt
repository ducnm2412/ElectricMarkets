package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electricmarkets.viewmodel.AuthViewModel
import com.example.electricmarkets.R

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val errorMessageState = authViewModel.errorMessage.observeAsState("")
    errorMessage = errorMessageState.value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.background(color = Color(0xFF009AEC))
        ) {
            Box(modifier = Modifier.fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 16.dp),
            ) {
                Text("Electric Markets",
                    color = Color(0xFFFBE025),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(start = 56.dp, top = 8.dp)
                )
                Card(onClick = {
                    navController.navigate("home")
                },
                    modifier = Modifier,
                ) {
                    Image(painter = painterResource(id = R.drawable.logoelectric),
                        contentDescription = null,
                        modifier = Modifier.size(56.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email or Phone") },
            modifier = Modifier
                .fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(onClick = {
            if (email.isEmpty() || password.isEmpty()) {
                authViewModel.setErrorMessage("Vui lòng nhập đầy đủ thông tin!")
            } else {
                // Gọi login với onSuccess callback để điều hướng đến Home
                authViewModel.login(email, password) {
                    // Điều hướng đến HomeScreen sau khi đăng nhập thành công
                    navController.navigate("home") {
                        // Đảm bảo người dùng không quay lại màn hình login
                        popUpTo("login") { inclusive = true }
                    }
                }
            }
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9DDEFB)
            )) {
            Text("Đăng nhập", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Chưa có tài khoản? Đăng ký",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable { navController.navigate("signup") } // Điều hướng đến màn hình đăng ký
                .padding(8.dp)
        )
    }
}

