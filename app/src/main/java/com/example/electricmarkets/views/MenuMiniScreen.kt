package com.example.electricmarkets.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electricmarkets.R

@Composable
fun MenuMiniSCreen(){
    val menuMinis = List(7) {
        MenuMini(
            name = "Máy lạnh",
            isImage = R.drawable.maylanh
        )
    }
    Row(modifier = Modifier.fillMaxWidth().background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically) {
        Card(onClick = {},
            modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp, top = 8.dp, end = 8.dp)
                .size(72.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.menu),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).weight(1f),
                    contentScale = ContentScale.Fit
                )

                Text("Danh mục",
                    fontSize = 12.sp,
                    modifier = Modifier,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        LazyRow(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            contentPadding = PaddingValues(start = 1.dp, end = 1.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(menuMinis){ menuMini->
                MenuMiniCard(menuMini)
            }
        }

    }
}
@Composable
fun MenuMiniCard(menuMini: MenuMini){
    Card(onClick = {},
        modifier = Modifier.size(72.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(4.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = menuMini.isImage),
                contentDescription = null,
                modifier = Modifier.size(56.dp).weight(1f),
                contentScale = ContentScale.Crop)
            Text(text = menuMini.name,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
        }
    }
}

data class MenuMini(
    val name: String,
    val isImage: Int
)