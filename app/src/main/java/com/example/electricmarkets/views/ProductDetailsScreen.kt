package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.electricmarkets.R
import com.google.firebase.auth.FirebaseAuth
import model.data.CartItem
import viewmodel.CartViewModel
import viewmodel.ProductViewModel

@Composable
fun ProductDetailScreen(navController: NavController, productId: String) {
    val productViewModel: ProductViewModel = viewModel()
    val product by productViewModel.productDetails.observeAsState()
    val errorMessage by productViewModel.errorMessage.observeAsState()
    val cartViewModel: CartViewModel = viewModel()

    LaunchedEffect(productId) {
        productViewModel.getProductDetails(productId)
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =Arrangement.Center) {
        HeadderScreen(productViewModel = ProductViewModel(), navController = navController)

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFDDDDDD)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card (onClick = {navController.navigate("home")},
                    modifier = Modifier.padding(start = 16.dp)) {
                    Image(painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp))
                }
                Text("Chi tiết sản phẩm",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 32.dp))
            }
        }
        if (errorMessage != null) {
            Text(text = errorMessage ?: "", color = Color.Red, fontWeight = FontWeight.Bold)
        } else {
            product?.let {
                Image(
                    painter = painterResource(it.image),
                    contentDescription = "Product Image",
                    modifier = Modifier.size(250.dp).fillMaxWidth().padding(16.dp),
                )

                // Tên sản phẩm và giá
                Text(text = it.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "${it.price} đ", fontSize = 18.sp, color = Color.Green)

                Text(text = it.description ?: "No description available.", fontSize = 14.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        onClick = {
                            val cartItem = CartItem(
                                productID = it.id,
                                productName = it.name,
                                quantity = 1,
                                price = it.discountedPrice,
                                imageRes = it.image // Dùng đúng giá trị hình ảnh của sản phẩm
                            )
                            cartViewModel.addToCart(
                                userId = FirebaseAuth.getInstance().currentUser?.uid ?: "",
                                productId = it.id,
                                item = cartItem
                            )
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Thêm vào giỏ", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            val cartItem = CartItem(
                                productID = it.id,
                                productName = it.name,
                                quantity = 1,
                                price = it.discountedPrice,
                                imageRes = it.image // Dùng đúng giá trị hình ảnh của sản phẩm
                            )
                            cartViewModel.addToCart(
                                userId = FirebaseAuth.getInstance().currentUser?.uid ?: "",
                                productId = it.id,
                                item = cartItem
                            )
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Mua ngay", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        FooterScreen(navController = navController)
    }
}

