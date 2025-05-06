package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
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
import model.data.Order
import viewmodel.OrderViewModel
import viewmodel.ProductViewModel

@Composable
fun OrderHistoryScreen(navController: NavController, orderViewModel: OrderViewModel) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    val orders by orderViewModel.orderList.observeAsState(emptyList())

    // Fetch orders when the screen is displayed
    LaunchedEffect(Unit) {
        orderViewModel.getOrdersByUser(userID = userId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
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
                Card (onClick = {navController.navigate("profile")},
                    modifier = Modifier.padding(start = 16.dp)) {
                    Image(painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp))
                }
                Text("Lịch sử đơn hàng",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 32.dp))
            }
        }

        // Order List
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .weight(1f)
        ) {
            items(orders) { order ->
                OrderCard(order = order) {
                    navController.navigate("orderDetailsScreen") // Navigate to order details screen
                }
            }
        }
        FooterScreen(navController = navController)

    }
}

@Composable
fun OrderCard(order: Order, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
            .border(1.dp, Color.LightGray)
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Text("Mã đơn hàng: ${order.orderID}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text("Tổng tiền: ${order.totalAmount} đ", fontSize = 14.sp)
        Text("Trạng thái: ${order.status}", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))

        // Loop through each order item
        order.items.forEach { item ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = item.productName, modifier = Modifier.weight(1f))
                Text(text = "${item.quantity} x ${item.price} đ", modifier = Modifier.weight(0.4f))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}
