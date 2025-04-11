package com.example.electricmarkets.views

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
    onSignUpClick: (String, String) -> Unit,
    onLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Collect LiveData state
    val errorMessage by authViewModel.errorMessage.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Đăng Ký", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Nhập lại mật khẩu") },
            modifier = Modifier.fillMaxWidth(),
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

        Button(
            onClick = {
                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    authViewModel.setErrorMessage("Vui lòng nhập đầy đủ!")
                } else if (password != confirmPassword) {
                    authViewModel.setErrorMessage("Mật khẩu không khớp!")
                } else if (!isValidEmail(email)) {
                    authViewModel.setErrorMessage("Email không hợp lệ!")
                } else if (password.length < 6 || !password[0].isUpperCase()) {
                    authViewModel.setErrorMessage("Mật khẩu phải có ít nhất 6 kí tự và chữ cái đầu phải viết hoa!")
                } else {
                    authViewModel.register(email, password) {
                        // Quay lại màn hình đăng nhập sau khi đăng ký thành công
                        navController.popBackStack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Đăng Ký")
        }

        Spacer(modifier = Modifier.height(16.dp))

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



