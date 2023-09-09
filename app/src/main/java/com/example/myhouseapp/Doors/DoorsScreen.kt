package com.example.myhouseapp.Doors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myhouseapp.ItemDataBase
import com.example.myhouseapp.R

@Composable
fun DoorsScreen(doorList: List<ItemDataBase>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 21.dp, vertical = 10.dp)
    ) {
        items(doorList) { model ->
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(size = 12.dp))
                    .background(Color.White)
            ) {
                CardDoor(model = model)
            }
        }
    }
}

@Composable
fun CardDoor(model: ItemDataBase) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        model = ImageRequest.Builder(LocalContext.current)
            .data(model.snapshot)
            .crossfade(true)
            .build(),
        contentDescription = "Door"
    )
    Text(
        modifier = Modifier
            .wrapContentWidth()
            .padding(16.dp),
        text = model.name!!,
        style = TextStyle(
            fontSize = 17.sp,
            fontFamily = FontFamily(Font(R.font.circe)),
            fontWeight = FontWeight.Bold,
            color = Color(0xFF555555),
        )
    )
}