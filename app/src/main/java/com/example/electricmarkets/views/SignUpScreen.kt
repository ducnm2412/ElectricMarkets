package com.example.electricmarkets.views

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electricmarkets.viewmodel.AuthViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electricmarkets.R
@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
    onSignUpClick: (String, String, String, String, String) -> Unit,
    onLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }  // fullName input
    var phone by remember { mutableStateOf("") }     // phone input
    var address by remember { mutableStateOf("") }   // address input

    // Collect LiveData state
    val errorMessage by authViewModel.errorMessage.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // header
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

        // Email input
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )

        // Password input
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

        // Confirm Password input
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Nhập lại mật khẩu") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            )
        )

        // Full Name input
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Họ và tên") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(12.dp),
        )

        // Phone input
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Số điện thoại") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(12.dp),
        )

        // Address input
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Địa chỉ") },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(12.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Show error message if needed
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Sign up button
        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    authViewModel.setErrorMessage("Vui lòng nhập đầy đủ!")
                } else if (password != confirmPassword) {
                    authViewModel.setErrorMessage("Mật khẩu không khớp!")
                } else if (!isValidEmail(email)) {
                    authViewModel.setErrorMessage("Email không hợp lệ!")
                } else if (password.length < 6 || !password[0].isUpperCase()) {
                    authViewModel.setErrorMessage("Mật khẩu phải có ít nhất 6 kí tự và chữ cái đầu phải viết hoa!")
                } else {
                    onSignUpClick(email, password, fullName, phone, address)  // Pass all parameters
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9DDEFB)
            )
        ) {
            Text("Đăng ký", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Go to login screen if already have an account
        Text(
            text = "Đã có tài khoản? Đăng nhập",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable { onLoginClick() }
                .padding(8.dp)
        )
    }
}

// Email validation function
fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}



