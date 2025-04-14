package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electricmarkets.R
import viewmodel.ProductViewModel

@Composable
fun HomeScreen() {
    val productViewModel: ProductViewModel = viewModel()
    val products = List(7) {
        Product(
            name = "Tủ lạnh SamSum inverter",
            price = "19.790.000",
            oldPrice = "22.490.000",
            imageRes = R.drawable.tulanh
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
        // Header và các Menu
        HeadderScreen()
        MenuMiniSCreen()

        // Quảng cáo
        Card(onClick = {},
            modifier = Modifier,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )) {
            Image(painter = painterResource(id = R.drawable.quangcao),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillWidth)
        }

        // Danh mục sản phẩm giảm giá
        Column(
            modifier = Modifier.fillMaxWidth().background(color = Color(0xFF9DDEFB))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("SẢN PHẨM ĐANG GIẢM GIÁ",
                    fontSize = 16.sp,
                    color = Color(0xFF165273),
                    modifier = Modifier.padding(0.dp)
                )
                Card(onClick = {},
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF9DDEFB)
                    )) {
                    Text("Xem thêm sản phẩm  --->",
                        color = Color(0xFF165273),
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.padding(0.dp))
                }
            }

            // Hiển thị sản phẩm
            Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)) {
                LazyRow(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(products) { product ->
                        ProductCard(product, onBuyClick = {
                            productViewModel.addDefaultProducts() // Thêm sản phẩm vào Realtime Database khi mua
                        })
                    }
                }
            }
        }

        // Phần sản phẩm khác (grid, v.v.)
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ProductGrid()
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, onBuyClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "Giá: ${product.price}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Giá cũ: ${product.oldPrice}",
                fontSize = 12.sp,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough
            )
            Button(
                onClick = onBuyClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Mua")
            }
        }
    }
}
