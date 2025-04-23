package com.example.electricmarkets.views

import android.widget.RadioButton
import androidx.compose.foundation.Image
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
import viewmodel.ProductViewModel

@Composable
fun FeedBackScreen(){
    var gender by remember { mutableStateOf("Anh") }
    var feedback by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeadderScreen(productViewModel = ProductViewModel())

        Box{
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Text("Góp ý/Liên hệ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Image(painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier.size(56.dp).padding(8.dp))
        }

        Text("Chúng tôi xin hân hạnh được hỗ trợ quý khách")

        // TextField góp ý
        OutlinedTextField(
            value = feedback,
            onValueChange = { feedback = it },
            placeholder = { Text("Nội dung (xin quý khách mô tả chi tiết)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 5,
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // RadioButton chọn giới tính
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = gender == "Anh",
                onClick = { gender = "Anh" },
                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF3B49FF))
            )
            Text("Anh", modifier = Modifier.padding(end = 16.dp))

            RadioButton(
                selected = gender == "Chị",
                onClick = { gender = "Chị" },
                colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF3B49FF))
            )
            Text("Chị")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Họ tên
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Họ và Tên") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Số điện thoại
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Số điện thoại") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nút gửi
        Button(
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9DDEFB)),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Gửi góp ý", color = Color.White, fontSize = 16.sp)
        }

    }
}