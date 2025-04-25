package com.example.electricmarkets.views

import android.widget.RadioButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.data.Feedback
import com.example.electricmarkets.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import model.data.Contact
import viewmodel.ContactViewModel
import viewmodel.ProductViewModel
@Composable
fun FeedBackScreen(navController: NavController, contactViewModel: ContactViewModel) {
    var gender by remember { mutableStateOf("Nam") }
    var feedback by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        // Header phần logo và tiêu đề
        Box(modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 16.dp)) {
            Text("Electric Markets", color = Color(0xFFFBE025), fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }

        // Phần nhập thông tin góp ý
        OutlinedTextField(
            value = feedback,
            onValueChange = { feedback = it },
            placeholder = { Text("Nội dung (xin quý khách mô tả chi tiết)") },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            maxLines = 5,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // RadioButton chọn giới tính
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = gender == "Nam",
                onClick = { gender = "Nam" },
                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF3B49FF))
            )
            Text("Nam", modifier = Modifier.padding(end = 16.dp))

            RadioButton(
                selected = gender == "Nữ",
                onClick = { gender = "Nữ" },
                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF3B49FF))
            )
            Text("Nữ")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Họ tên
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Họ và Tên") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        // Số điện thoại
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Số điện thoại") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        // Nút gửi
        Button(
            onClick = {
                val userID = FirebaseAuth.getInstance().currentUser?.uid ?: return@Button
                val contact = Contact(
                    message = feedback,
                    gender = gender,
                    email = email,
                    phone = phone
                )

                // Gọi phương thức submit để lưu vào Realtime Database
                contactViewModel.submitContact(userID, contact) { success ->
                    if (success) {
                        // Hiển thị thông báo thành công
                        println("Góp ý đã được gửi thành công")
                    } else {
                        // Hiển thị thông báo thất bại
                        println("Lỗi khi gửi góp ý")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9DDEFB)),
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Gửi góp ý", color = Color.White, fontSize = 16.sp)
        }
    }
}
