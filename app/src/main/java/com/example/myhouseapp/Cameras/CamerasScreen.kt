package com.example.myhouseapp.Cameras

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhouseapp.Cameras.DataClassesCameras.CameraModel
import com.example.myhouseapp.R

private val cameraList = mutableListOf<CameraModel>()

@Preview
@Composable
fun CameraScreen() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(21.dp, 15.dp),
            text = "Гостиная",
            style = TextStyle(
                fontSize = 21.sp,
                fontFamily = FontFamily(Font(R.font.circe)),
                fontWeight = FontWeight(300),
                color = Color(0xFF333333),
            )
        )

    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(cameraList) { model ->
            CardCamera(model = model)
        }
    }
}

@Composable
fun CardCamera(model: CameraModel) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.camera_icon),
            contentDescription = "Camera"
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "ABC",
            style = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.circe)),
                fontWeight = FontWeight(400),
                color = Color(0xFF555555),
            )
        )
    }
}
