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
import viewmodel.ProductViewModel

@Composable
fun OrderDetailsScreen(navController: NavController, orderViewModel: OrderViewModel) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    val orders by orderViewModel.orderList.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        orderViewModel.getLatestOrder(userID = userId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HeadderScreen(productViewModel = ProductViewModel(), navController = navController)
        Text(
            "THÔNG TIN ĐƠN HÀNG",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.White,
            modifier = Modifier
                .background(Color(0xFF009AEC))
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        // Customer Information
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Tên: ${FirebaseAuth.getInstance().currentUser?.displayName ?: "N/A"}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            "Email: ${FirebaseAuth.getInstance().currentUser?.email ?: "N/A"}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        // Address fetched from orders (if available)
        Text(
            "Địa chỉ: ${orders.firstOrNull()?.shippingAddress ?: "N/A"}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )

        // Order Details Table with reduced height for rows
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
                .border(1.dp, Color.LightGray)
                .padding(8.dp) // Reduced padding for the table to make it more compact
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "STT", modifier = Modifier.weight(0.2f), fontSize = 14.sp)
                Text(text = "Tên", modifier = Modifier.weight(1f), fontSize = 14.sp)
                Text(text = "SL", modifier = Modifier.weight(0.3f), fontSize = 14.sp)
                Text(text = "Giá", modifier = Modifier.weight(0.4f), fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Loop through each order item
            var index = 1
            orders.forEach { order ->
                order.items.forEach { item ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "$index", modifier = Modifier.weight(0.2f), fontSize = 14.sp)
                        Text(
                            text = item.productName,
                            modifier = Modifier.weight(1f),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${item.quantity}",
                            modifier = Modifier.weight(0.3f),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "${item.price} đ",
                            modifier = Modifier.weight(0.4f),
                            fontSize = 14.sp
                        )
                    }
                    index++
                    Spacer(modifier = Modifier.height(4.dp))  // Reduced spacing for compactness
                }
            }
        }

        // Total Price
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Tổng tiền:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            val totalAmount = orders.sumOf { order -> order.items.sumOf { it.price * it.quantity } }
            Text(text = "$totalAmount đ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        // QR code and footer
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.qr_code_image),  // Replace with your QR image
            contentDescription = "QR Code",
            modifier = Modifier
                .size(100.dp)  // Adjusted size for QR code
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Quét mã QR để thanh toán",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 14.sp,
            color = Color.Gray

        )
        FooterScreen(navController = navController)

    }
}