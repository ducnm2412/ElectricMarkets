package com.example.electricmarkets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.electricmarkets.ui.theme.ElectricMarketsTheme
import com.example.electricmarkets.navigation.Navigation
import com.example.electricmarkets.views.CartScreen
import com.example.electricmarkets.views.FeedBackScreen
import com.example.electricmarkets.views.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElectricMarketsTheme {
                // Gọi Navigation function để hiển thị các màn hình trong app
                Navigation()
            }
        }
    }
}
