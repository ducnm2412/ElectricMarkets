package com.example.electricmarkets.views



import androidx.annotation.Dimension.Companion.DP
import androidx.annotation.Dimension.Companion.DP
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricmarkets.R

@Composable
fun AccountScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF26A9EF))
                .padding(top = 20.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp)
                .padding(bottom = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imageshop),
                        contentDescription = "Logo",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(56.dp))
                    Text(
                        "CHỢ ĐIỆN MÁY",
                        color = Color.Yellow,
                        fontSize = 26.sp,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White, shape = RoundedCornerShape(25.dp)),
                    shape = RoundedCornerShape(25.dp),
                    placeholder = { Text("Tìm Kiếm...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF26A9EF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Đăng nhập")
            }

            Text("Hoặc", modifier = Modifier.padding(vertical = 8.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF26A9EF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Đăng ký")
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            "Hỗ trợ khách hàng",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(start = 6.dp)
        )
        SupportItem(
            text = "Tổng đài tư vấn: 1900 1908 (7:00 - 21:30)",
            iconResId = R.drawable.ic_call,
            enabled = false
        )

        SupportItem(
            text = "Góp ý/ Liên hệ",
            iconResId = R.drawable.ic_post,
            enabled = true,
//            onClick = { navController.navigate("feedback_screen") }
        )

        SupportItem(
            text = "Tìm kiếm siêu thị",
            iconResId = R.drawable.ic_location,
            enabled = true,
//            onClick = { navController.navigate("store_locator_screen") }
        )

        SupportItem(
            text = "Giới thiệu công ty",
            iconResId = R.drawable.ic_info,
            enabled = true,
//            onClick = { navController.navigate("about_us_screen") }
        )

        SupportItem(
            text = "Lịch sử đặt hàng",
            iconResId = R.drawable.ic_history,
            enabled = true,
//            onClick = { navController.navigate("order_history_screen") }
        )
    }
}


@Composable
fun SupportItem(
    text: String,
    @DrawableRes iconResId: Int,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(color = Color(0xFFD9D9D9))
            .fillMaxWidth()
            .height(50.dp)
            .border(width = 1.dp, color = Color.Gray)
            .padding(horizontal = 8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Icon",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = text,
                    color = if (enabled) Color.Black else Color.Gray,
                    fontSize = 16.sp
                )
            }

            if (enabled) {
                IconButton(onClick = onClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_next),
                        contentDescription = "Next",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}
