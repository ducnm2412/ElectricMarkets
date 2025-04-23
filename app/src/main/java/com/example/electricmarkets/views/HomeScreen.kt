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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.electricmarkets.ProductCard
import com.example.electricmarkets.R
import viewmodel.ProductViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.electricmarkets.viewmodel.AuthViewModel
import viewmodel.CartViewModel


@Composable
fun HomeScreen() {
    val productViewModel: ProductViewModel = viewModel()  // Lấy ViewModel
    val products by productViewModel.productList.observeAsState(emptyList())
    val cartViewModel: CartViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()

    // Gọi getProducts() để lấy dữ liệu từ Firebase khi màn hình được hiển thị
    LaunchedEffect(true) {
        productViewModel.getProducts()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeadderScreen(productViewModel = productViewModel)

        MenuMiniSCreen()

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            item {
                // Quảng cáo
                Card(
                    onClick = {},
                    modifier = Modifier
                        .height(140.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.quangcao),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .background(color = Color(0xFF9DDEFB))
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

                    Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)) {
                        LazyRow(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                        ) {
                            items(products) { product ->
                                ProductCard(product = product, cartViewModel = cartViewModel, authViewModel = authViewModel)
                            }
                        }
                    }
                }
            }

            item {
                Text(
                    "GỢI Ý CHO BẠN",
                    fontSize = 16.sp,
                    color = Color(0xFF165273),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Start
                )
            }
            items(products.chunked(3)) { rowProducts ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    for (product in rowProducts) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
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


