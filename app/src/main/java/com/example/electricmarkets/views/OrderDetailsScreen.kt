package com.example.electricmarkets.views
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.google.firebase.auth.FirebaseAuth
import viewmodel.OrderViewModel

@Composable
fun OrderDetailsScreen(navController: NavController, orderViewModel: OrderViewModel) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "" // Lấy userId từ Firebase
    val orders by orderViewModel.orderList.observeAsState(emptyList()) // Quan sát dữ liệu đơn hàng

    // Lấy đơn hàng khi màn hình được hiển thị
    LaunchedEffect(Unit) {
        orderViewModel.getOrdersByUser(userID = userId)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Thông Tin Đơn Hàng", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.White)

        // Kiểm tra nếu không có đơn hàng
        if (orders.isEmpty()) {
            Text("Không có đơn hàng", color = Color.Gray)
        } else {
            // Hiển thị các đơn hàng
            orders.forEach { order ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text("Mã đơn hàng: ${order.orderID}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text("Tổng tiền: ${order.totalAmount} đ", fontSize = 14.sp)
                    order.items.forEach { item ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(item.productName, modifier = Modifier.weight(1f))
                            Text("SL: ${item.quantity}", modifier = Modifier.weight(0.5f))
                            Text("${item.price} đ", modifier = Modifier.weight(0.5f))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                }
            }
        }
    }
}
