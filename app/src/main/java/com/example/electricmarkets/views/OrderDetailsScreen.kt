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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricmarkets.R

@Composable
fun OrderDetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "THÔNG TIN ĐƠN HÀNG",
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
        Text(text = "Tên: Phạm Quốc Huy")
        Text(text = "Email: abc@gmail.com")
        Text(text = "Ngày: 3/2/2025")
        Text(text = "Giờ: 21:39")
        Text(text = "Địa chỉ: ở campuchia")

        // Order Details Table
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .background(Color.White)
                .border(1.dp, Color.LightGray)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "STT", modifier = Modifier.weight(0.2f))
                Text(text = "Tên", modifier = Modifier.weight(1f))
                Text(text = "SL", modifier = Modifier.weight(0.3f))
                Text(text = "Giá", modifier = Modifier.weight(0.4f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            // First product
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "1", modifier = Modifier.weight(0.2f))
                Text(text = "Máy lạnh Nagakawa Inverter 1 HP", modifier = Modifier.weight(1f))
                Text(text = "1", modifier = Modifier.weight(0.3f))
                Text(text = "6.490.000 đ", modifier = Modifier.weight(0.4f))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            // Second product
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "2", modifier = Modifier.weight(0.2f))
                Text(text = "Smart Tivi Samsung 4K 65 inch", modifier = Modifier.weight(1f))
                Text(text = "1", modifier = Modifier.weight(0.3f))
                Text(text = "13.990.000 đ", modifier = Modifier.weight(0.4f))
            }
        }

        // Total Price
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Tổng tiền:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "20.480.000 đ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        // QR code and footer
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.qr_code_image),
            contentDescription = "QR Code",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Quét mã QR để thanh toán",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}
