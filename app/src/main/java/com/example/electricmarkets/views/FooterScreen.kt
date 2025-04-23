package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.electricmarkets.R


@Composable
fun FooterScreen(navController: NavController) {
    val items = listOf(
        Triple(R.drawable.home, "Trang chủ", "home"),
        Triple(R.drawable.sale1, "Khuyến mãi", "sale"),
        Triple(R.drawable.shopping, "Giỏ hàng", "cart"),
        Triple(R.drawable.profileee, "Cá nhân", "profile")
    )

    // Lấy route hiện tại
    val currentRoute = navController.currentBackStackEntry?.destination?.route


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF9DDEFB))
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items.forEach { (iconRes, label, route) ->
            val isSelected = currentRoute == route

            Card(
                onClick = {
                    if (!isSelected) {
                        navController.navigate(route) {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) Color.White else Color(0xFF9DDEFB),
                    contentColor = Color.Black
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            ) {
                Column(
                    modifier = Modifier.size(78.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(56.dp)
                    )
                    Text(label, fontSize = 14.sp)
                }
            }
        }
    }
}