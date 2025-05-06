package com.example.electricmarkets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electricmarkets.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import model.data.CartItem
import model.data.Product
import viewmodel.CartViewModel

@Composable
fun ProductCard(product: Product, cartViewModel: CartViewModel, authViewModel: AuthViewModel,navController: NavController) {
    val isLoggedIn = FirebaseAuth.getInstance().currentUser != null  // Kiểm tra trạng thái đăng nhập
    // Kiểm tra tài nguyên hình ảnh có hợp lệ không
    val imageRes = if (product.image != 0) product.image else R.drawable.ic_launcher_background // Hoặc một hình ảnh khác có sẵn

    Card(
        modifier = Modifier
            .width(160.dp)
            .aspectRatio(0.7f)
            .border(
                width = 2.dp,
                color = Color(0xFF009AEC),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                navController.navigate("productDetailScreen/${product.id}")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Đảm bảo tài nguyên hình ảnh hợp lệ
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "$${product.price}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    if (product.discountedPrice < product.price) {
                        Text(
                            text = "$${product.discountedPrice}",
                            fontSize = 12.sp,
                            color = Color.Red,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
                Spacer(modifier = Modifier.width(4.dp))

                // Nút Mua
                Button(
                    onClick = {
                        if (isLoggedIn) {  // Nếu người dùng đã đăng nhập
                            val cartItem = CartItem(
                                productID = product.id,
                                productName = product.name,
                                quantity = 1,
                                price = product.discountedPrice,
                                imageRes = product.image
                            )
                            // Lưu thông tin sản phẩm vào giỏ hàng
                            cartViewModel.addToCart(
                                userId = FirebaseAuth.getInstance().currentUser?.uid ?: "",
                                productId = product.id,
                                item = cartItem
                            )
                        } else {
                            // Nếu người dùng chưa đăng nhập, yêu cầu đăng nhập
                            println("Vui lòng đăng nhập trước khi thêm sản phẩm vào giỏ hàng.")
                        }
                    },
                    modifier = Modifier.height(28.dp),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE0E0E0),
                        contentColor = Color(0xFF165273)
                    ),
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp)
                ) {
                    Text("Mua", fontSize = 12.sp)
                }
            }
        }
    }
}

