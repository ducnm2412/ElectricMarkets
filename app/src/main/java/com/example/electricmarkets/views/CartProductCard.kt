package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricmarkets.R
import com.google.firebase.auth.FirebaseAuth
import model.data.CartItem
import model.data.Product
import viewmodel.CartViewModel

@Composable
fun CartProductCard(cartItem: CartItem, cartViewModel: CartViewModel) {
    val imageRes = when (cartItem.imageRes) {
        2131165346 -> R.drawable.panasonic_1hp
        2131165332 -> R.drawable.naga_1hp
        2131165355 -> R.drawable.tcl_15hp
        2131165295 -> R.drawable.electrolux_eci28d
        2131165360 -> R.drawable.tu_lanh_samsung
        2131165327 -> R.drawable.may_giat_panasonic
        2131165323 -> R.drawable.loc_nuoc_ro
        2131165362 -> R.drawable.xe_dap_robot
        2131165357 -> R.drawable.tivi_lg_55
        2131165274 -> R.drawable.casper_1hp
        else -> R.drawable.ic_launcher_background  // Hình ảnh mặc định
    }

    Card(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false, // Có thể thay đổi khi xử lý tình huống chọn item
                onCheckedChange = {}
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Hiển thị hình ảnh sản phẩm
            Image(
                painter = painterResource(id = imageRes), // Đây là hình ảnh của sản phẩm
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
                Text(text = cartItem.productName, fontWeight = FontWeight.Bold)
                Text(text = "Giá: ${cartItem.price}", color = Color.Gray, fontSize = 14.sp)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Số lượng: ", fontSize = 14.sp)
                    IconButton(onClick = {
                        if (cartItem.quantity > 1) {
                            // Giảm số lượng
                            cartViewModel.updateQuantity(cartItem.productID, cartItem.quantity - 1)
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Giảm")
                    }
                    Text(text = "${cartItem.quantity}", fontSize = 14.sp)
                    IconButton(onClick = {
                        // Tăng số lượng
                        cartViewModel.updateQuantity(cartItem.productID, cartItem.quantity + 1)
                    }) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Tăng")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            cartViewModel.removeItem(userId = FirebaseAuth.getInstance().currentUser?.uid ?: "", productId = cartItem.productID)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6B6B))
                    ) {
                        Text("Xóa khỏi giỏ")
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}


