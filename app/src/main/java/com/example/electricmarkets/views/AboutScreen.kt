package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import com.example.electricmarkets.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AboutScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.background(color = Color(0xFF26A9EF))
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(onClick = {navController.navigate("home")},
                    modifier = Modifier.padding(start = 24.dp)) {
                    Image(painter = painterResource(id = R.drawable.imageshop),
                        contentDescription =  null,
                        modifier = Modifier.size(56.dp))
                }
                Text("Electric Markets",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFBE025),
                    modifier = Modifier.weight(1f).padding(end = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFDDDDDD)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card (onClick = {navController.navigate("profile")},
                    modifier = Modifier.padding(start = 16.dp)) {
                    Image(painter = painterResource(id = R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp))
                }
                Text("Giới Thiệu Về Công Ty",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 32.dp))
            }
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Chợ Điện Máy là hệ thống mua sắm điện máy hàng đầu, chuyên cung cấp các sản phẩm điện tử, điện lạnh, gia dụng và công nghệ chính hãng với giá cả cạnh tranh. Với sứ mệnh mang đến sự tiện lợi, chất lượng và dịch vụ tận tâm, chúng tôi cam kết mang đến cho khách hàng trải nghiệm mua sắm hiện đại, nhanh chóng và an toàn.\n\n" +
                    "Chúng tôi không ngừng mở rộng hệ thống, hợp tác với các thương hiệu uy tín như Samsung, Sony, LG, Panasonic,… để đảm bảo mang đến những sản phẩm tốt nhất cùng các chương trình khuyến mãi hấp dẫn.\n\n" +
                    "Ngoài ra, Chợ Điện Máy còn chú trọng đến dịch vụ hậu mãi với chính sách bảo hành chính hãng, giao hàng nhanh chóng và đội ngũ chăm sóc khách hàng chuyên nghiệp",
                fontSize = 20.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp))
            Text("Hãy đến với Chợ Điện Máy – Nơi hội tụ công nghệ, nâng tầm cuộc sống!",
                fontSize = 22.sp,
                color = Color(0xFF26A9EF),
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp))
        }
    }
}
