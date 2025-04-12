package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun HeadderScreen(){
    Column(modifier = Modifier.background(color = Color(0xFF009AEC))
    ) {

        Box(modifier = Modifier.fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 16.dp),
        ) {
            Text("Electric Markets",
                color = Color(0xFFFBE025),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(start = 56.dp, top = 8.dp)
            )
            Card(
                modifier = Modifier,
            ) {
                Image(painter = painterResource(id = R.drawable.logoelectric),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 8.dp)
                .background(Color.White, shape = RoundedCornerShape(28.dp))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(28.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier = Modifier.size(48.dp))

            Spacer(modifier = Modifier.width(8.dp))

            Text("Tìm Kiếm ..............",
                fontSize = 20.sp,
                color = Color.LightGray)
        }
    }
}