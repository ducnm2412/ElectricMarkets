package com.example.electricmarkets.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.electricmarkets.Product
import com.example.electricmarkets.ProductCard
import com.example.electricmarkets.R

@Composable
fun MenuScreen(){

    val products = List(100) {
        Product(
            name = "Tủ lạnh SamSum inverter",
            price = "19.790.000",
            oldPrice = "22.490.000",
            imageRes = R.drawable.tulanh,
            quantity = null
        )
    }

    val menuMinis = List(12) {
        MenuMini(
            name = "Máy lạnh",
            isImage = R.drawable.maylanh
        )
    }

    Column(modifier = Modifier.fillMaxSize().background(color = Color.White)) {

        HeadderScreen()

        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {

            Column(
                modifier = Modifier.background(color = Color.White)
            ) {
                LazyColumn(modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                    contentPadding = PaddingValues(top = 1.dp, bottom = 1.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(menuMinis) { menuMini ->
                        MenuMiniCard(menuMini)
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF9DDEFB))
            ) {
                items(products.chunked(2)) { rowProducts ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
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

                        repeat(2 - rowProducts.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        FooterScreen()
    }
}