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
import com.example.electricmarkets.Product
import com.example.electricmarkets.R

@Composable
fun CartProductCard(product: Product){
    Card(modifier = Modifier.fillMaxWidth()
        .padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = {}
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column (modifier = Modifier.fillMaxWidth().weight(1f),
            ) {
                Text(text = product.name, fontWeight = FontWeight.Bold)
                Text(text = "Giá: ${product.oldPrice}", color = Color.Gray, fontSize = 14.sp)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Số lượng: ", fontSize = 14.sp)
                    IconButton (onClick = {  }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Giảm")
                    }
                    Text(text = "${product.quantity}", fontSize = 14.sp)
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Tăng")
                    }
                }

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button (
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6B6B)
                        )
                    ) {
                        Text("Xóa khỏi giỏ")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF9DDEFB)
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Mua")
                    }
                }
            }
        }

    }
}
