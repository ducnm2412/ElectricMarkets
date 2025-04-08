package com.example.electricmarkets.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.electricmarkets.R

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        // Tìm kiếm
        TextField(
            value = TextFieldValue(""),
            onValueChange = {},
            label = { Text("Tìm Kiếm...") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Sản phẩm giảm giá
        Text(text = "Sản phẩm giảm giá", style = MaterialTheme.typography.titleMedium)

        // Danh sách sản phẩm giảm giá
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(10) { index ->
                ProductItem()
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Gợi ý cho bạn
        Text(text = "Gợi ý cho bạn", style = MaterialTheme.typography.titleMedium)

        // Danh sách sản phẩm gợi ý
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(10) { index ->
                ProductItem()
            }
        }
    }
}

@Composable
fun ProductItem() {
    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Product Image",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Mã lạnh Panasonic", style = MaterialTheme.typography.bodyLarge)
        Text(text = "12,290,000đ", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
