package com.example.electricmarkets.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.electricmarkets.ProductCard
import com.example.electricmarkets.R
import com.google.android.gms.analytics.ecommerce.Product
import com.google.firebase.auth.FirebaseAuth
import viewmodel.CartViewModel
import viewmodel.ProductViewModel

@Composable
fun CartScreen(navController: NavController) {
    val cartViewModel: CartViewModel = viewModel()  // Lấy CartViewModel
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""  // Lấy userId từ Firebase Auth
    val cartItems by cartViewModel.cartItems.observeAsState(emptyList())

    // Tính tổng tiền và số lượng sản phẩm
    val totalAmount = cartItems.sumOf { it.price * it.quantity }
    val totalQuantity = cartItems.sumOf { it.quantity }

    LaunchedEffect(userId) {
        cartViewModel.loadCart(userId)  // Tải giỏ hàng khi userId thay đổi
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HeadderScreen(productViewModel = ProductViewModel(), navController =  navController) // Pass productViewModel here

        Column(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .background(color = Color(0xFF9DDEFB))
        ) {
            LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
                items(cartItems) { cartItem ->
                    // Hiển thị từng sản phẩm trong giỏ hàng
                    val product = model.data.Product(
                        id = cartItem.productID,
                        name = cartItem.productName,
                        price = cartItem.price,
                        discountedPrice = cartItem.price, // Assuming no discount in CartItem
                        category = "",  // Set as needed
                        stock = 0, // Set as needed
                        image = cartItem.imageRes
                    )
                    CartProductCard(cartItem = cartItem, cartViewModel = cartViewModel)
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            // Cập nhật kích thước chữ nhỏ hơn cho các dòng text
            Text("Tổng sản phẩm:", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("$totalQuantity", fontSize = 14.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.width(16.dp)) // Thêm khoảng cách giữa các phần tử

            Text("Tổng tiền:", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("$totalAmount đ", fontSize = 14.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.weight(1f))  // Cấp phần không gian còn lại cho nút mua

            // Nút mua
            Button(
                onClick = {
                    // Gọi phương thức checkoutCart khi người dùng bấm nút "Mua"
                    cartViewModel.checkoutCart(
                        userUID = userId,
                        shippingAddress = "Địa chỉ giao hàng",
                        onResult = { success, message ->
                            if (success) {
                                navController.navigate("orderDetailsScreen")
                            } else {
                                println(message)
                            }
                        }
                    )
                },
                modifier = Modifier
                    .padding(8.dp)  // Thêm padding cho nút
                    .height(50.dp)  // Đặt chiều cao cho nút
                    .width(120.dp),  // Đặt chiều rộng của nút
                shape = RoundedCornerShape(20.dp),  // Bo góc cho nút
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9DDEFB)),  // Màu nền của nút
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp, pressedElevation = 12.dp)  // Thêm bóng cho nút khi nhấn
            ) {
                Text(
                    "Mua",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,  // Màu chữ trắng
                    fontSize = 18.sp
                )
            }
        }

        FooterScreen(navController = navController)
    }
}
