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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.electricmarkets.ProductCard
import com.example.electricmarkets.R
import com.example.electricmarkets.viewmodel.AuthViewModel
import viewmodel.CartViewModel
import viewmodel.ProductViewModel

@Composable
fun MenuScreen(navController: NavController){

    val productViewModel: ProductViewModel = viewModel()  // Lấy ViewModel
    val cartViewModel: CartViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()
    val products by productViewModel.productList.observeAsState(emptyList())

    val menuMinis = List(12) {
        MenuMini(
            name = "Máy lạnh",
            isImage = R.drawable.maylanh
        )
    }

    Column(modifier = Modifier.fillMaxSize().background(color = Color.White)) {

        HeadderScreen(productViewModel = ProductViewModel(),navController = navController)

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
                                ProductCard(product = product, cartViewModel = cartViewModel, authViewModel = authViewModel,navController = navController)
                            }
                        }

                        repeat(2 - rowProducts.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
        FooterScreen(navController = navController)
    }
}