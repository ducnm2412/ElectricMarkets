package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electricmarkets.R
import viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadderScreen(productViewModel: ProductViewModel, navController: NavController) {
    val searchKeyword = remember { mutableStateOf("") }  // Dùng để lưu từ khóa tìm kiếm
    // Thêm Box để chứa các phần còn lại của Header
    Column(modifier = Modifier.background(color = Color(0xFF009AEC))) {
        Box(modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 16.dp)) {
            Text("Electric Markets",
                color = Color(0xFFFBE025),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(start = 56.dp, top = 8.dp)
            )

            // Khi nhấn vào logo, chuyển hướng về Home screen
            Card(onClick = {
                navController.navigate("home")
            }) {
                Image(
                    painter = painterResource(id = R.drawable.logoelectric),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
            }
        }

        // Tạo một Row chứa Image và TextField cho tìm kiếm
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp)
                .background(Color.White, shape = RoundedCornerShape(28.dp))
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(28.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Hình ảnh tìm kiếm
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Tìm kiếm",
                modifier = Modifier
                    .size(48.dp)
                    .clickable { // Khi nhấn vào hình ảnh, thực hiện tìm kiếm
                        productViewModel.searchProductsByName(searchKeyword.value)
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Hiển thị TextField cho người dùng nhập từ khóa tìm kiếm
            TextField(
                value = searchKeyword.value,
                onValueChange = { searchKeyword.value = it },
                label = { Text("Tìm kiếm sản phẩm...") },
                shape = RoundedCornerShape(28.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
