package com.example.electricmarkets.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricmarkets.Product
import com.example.electricmarkets.ProductCard
import com.example.electricmarkets.R

@Composable
fun CartScreen(){

    val products = List(50) {
        Product(
            name = "Tủ lạnh SamSum inverter",
            price = "19.790.000",
            oldPrice = "22.490.000",
            imageRes = R.drawable.tulanh,
            quantity = 2
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {

        HeadderScreen()

        MenuMiniSCreen()

        Column(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .background(color = Color(0xFF9DDEFB))
        ) {
            LazyColumn(modifier = Modifier.padding(top = 8.dp)
            ) {
                items(products) { product ->
                    CartProductCard(product)
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text("Tổng sản phẩm:", fontSize = 16.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.width(8.dp))
            //tính số lượng
            Text("2", fontSize = 16.sp,fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(24.dp))
            Text("Tổng tiền:", fontSize = 16.sp,fontWeight = FontWeight.Bold)
            //tính tổng
            Text("20.480.000", fontSize = 16.sp,fontWeight = FontWeight.Bold)
            Text("đ", fontSize = 16.sp,fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9DDEFB)
                ),
            ) {
                Text("Mua",fontWeight = FontWeight.Bold)
            }
        }

        FooterScreen()
    }
}