package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.electricmarkets.Product
import com.example.electricmarkets.ProductCard
import com.example.electricmarkets.R

@Composable
fun SaleScreen(){
    val products = List(50) {
        Product(
            name = "Tủ lạnh SamSum inverter",
            price = "19.790.000",
            oldPrice = "22.490.000",
            imageRes = R.drawable.tulanh,
            quantity = null
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {

        HeadderScreen()

        MenuMiniSCreen()

        LazyColumn(modifier = Modifier.fillMaxWidth()
            .weight(1f).background(color = Color(0xFF9DDEFB))) {

            item{
                Card(
                    onClick = {},
                    modifier = Modifier
                        .height(140.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sale2),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            item{
                Card(
                    onClick = {},
                    modifier = Modifier.height(35.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imagesale1),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
            items(products.chunked(3)) { rowProducts ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    for (product in rowProducts) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            ProductCard(product)
                        }
                    }

                    repeat(3 - rowProducts.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        FooterScreen()
    }
}