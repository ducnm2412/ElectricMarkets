package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricmarkets.R
import viewmodel.ProductViewModel

@Composable
fun ProfileScreen(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeadderScreen(productViewModel = ProductViewModel())

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9DDEFB)
            )) {
            Text("Đăng nhập", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }

        Text("Hoặc",
            color = Color(0xFFB3B3B3))

        Button(onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9DDEFB)
            )) {
            Text("Đăng kí", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Hỗ trợ khách hàng",
            color = Color.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        // tổng đài tư vấn
        Card(onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.phonecall),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).padding(end = 8.dp))
                Text("Tổng đài tư vấn: 1900 1908 (7:00-21:30)",
                    color = Color.Black,
                    fontSize = 18.sp)
            }
        }
        // góp ý liên hệ
        Card(onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.mail),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).padding(end = 8.dp))
                Text("Góp ý/Liên hệ",
                    color = Color.Black,
                    fontSize = 18.sp)
            }
        }
        // ti kiếm siu thị
        Card(onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.map),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).padding(end = 8.dp))
                Text("Tìm kiếm siêu thị",
                    color = Color.Black,
                    fontSize = 18.sp)
            }
        }
        //giới thiệu công ty
        Card(onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.bookmark),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).padding(end = 8.dp))
                Text("Giới thiệu công ty ",
                    color = Color.Black,
                    fontSize = 18.sp)
            }
        }
        // lịch sử đặt hàng
        Card(onClick = {},
            modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row(modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.clock),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).padding(end = 8.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFB3B3B3)))
                Text("Lịch sử đặt hàng",
                    color = Color.Black,
                    fontSize = 18.sp)
            }
        }

        FooterScreen()

    }
}