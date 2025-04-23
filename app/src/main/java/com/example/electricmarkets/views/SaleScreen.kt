package com.example.electricmarkets.views

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electricmarkets.ProductCard
import com.example.electricmarkets.R
import com.example.electricmarkets.viewmodel.AuthViewModel
import viewmodel.CartViewModel
import viewmodel.ProductViewModel
@Composable
fun SaleScreen() {
    val cartViewModel: CartViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel() // Lấy ViewModel
    val authViewModel: AuthViewModel = viewModel()

    // Quan sát danh sách sản phẩm
    val products by productViewModel.productList.observeAsState(emptyList())

    // Lấy dữ liệu sản phẩm từ Firebase khi màn hình được hiển thị
    LaunchedEffect(true) {
        productViewModel.getProducts()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HeadderScreen(productViewModel = productViewModel)
        MenuMiniSCreen()

        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color(0xFF9DDEFB))) {
            item {
                // Quảng cáo
                Card(
                    onClick = {},
                    modifier = Modifier.height(140.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sale2),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            item {
                // Quảng cáo khác
                Card(
                    onClick = {},
                    modifier = Modifier.height(35.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imagesale1),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            // Hiển thị sản phẩm
            items(products.chunked(3)) { rowProducts ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    for (product in rowProducts) {
                        Box(modifier = Modifier.weight(1f)) {
                            ProductCard(product = product, cartViewModel = cartViewModel, authViewModel = authViewModel)
                        }
                    }

                    repeat(3 - rowProducts.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        FooterScreen()
    }
}
