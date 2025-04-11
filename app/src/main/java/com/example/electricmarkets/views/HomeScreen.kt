package com.example.electricmarkets.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.electricmarkets.R
import model.data.Product
import viewmodel.ProductViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(productViewModel: ProductViewModel = viewModel()) {
    val context = LocalContext.current
    val productList by productViewModel.productList.observeAsState(listOf())

    Column(modifier = Modifier.padding(16.dp)) {

        // ✅ Nút thêm sản phẩm mẫu
        Button(
            onClick = {
                productViewModel.addDefaultProducts()
                Toast.makeText(context, "✅ Đã thêm sản phẩm mẫu!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("➕ Thêm sản phẩm mẫu")
        }

        // 🔍 Tìm kiếm
        TextField(
            value = TextFieldValue(""),
            onValueChange = {},
            label = { Text("Tìm Kiếm...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // 🔖 Sản phẩm giảm giá
        Text(text = "Sản phẩm giảm giá", style = MaterialTheme.typography.titleMedium)
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(productList) { product ->
                ProductItem(product = product)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 💡 Gợi ý cho bạn
        Text(text = "Gợi ý cho bạn", style = MaterialTheme.typography.titleMedium)
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(productList) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Product Image",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
        Text(text = "${product.price}đ", color = Color.Red, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
