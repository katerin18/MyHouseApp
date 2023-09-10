package com.example.myhouseapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhouseapp.Cameras.CameraRepository
import com.example.myhouseapp.Cameras.CamerasScreen
import com.example.myhouseapp.Doors.DoorRepository
import com.example.myhouseapp.Doors.DoorsScreen

class MainActivity : ComponentActivity() {
    private lateinit var doorRepository: DoorRepository
    private lateinit var cameraRepository: CameraRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        doorRepository = DoorRepository()
        cameraRepository = CameraRepository()

        if (doorRepository.getDoors().value.isEmpty()) {
            doorRepository.saveDoors()
        }

        if (cameraRepository.getCameras().value.isEmpty()) {
            cameraRepository.saveCameras()
        }

        setContent {
            MainTitle()
        }
    }
    @Preview
    @Composable
    fun MainTitle() {
        Column() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 29.dp)
            ) {
                Text(
                    text = "Мой дом",
                    style = TextStyle(
                        fontSize = 21.sp,
                        lineHeight = 26.sp,
                        fontFamily = FontFamily(Font(R.font.circe)),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            TabScreen()
        }
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun TabScreen() {
        var tabIndex by remember { mutableStateOf(0) }
        val tabs = listOf("Камеры", "Двери")

        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(
                selectedTabIndex = tabIndex
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Text(
                                text = title,
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    lineHeight = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.circe)),
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF333333),
                                    textAlign = TextAlign.Center,
                                )
                            )
                        },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            val dataDoors by doorRepository.getDoors().collectAsState()
            val dataCameras by cameraRepository.getCameras().collectAsState()

            LaunchedEffect(key1 = Unit) {
                doorRepository.getDoors()
                cameraRepository.getCameras()
            }
            when (tabIndex) {
                0 -> {
                    val cameraScreen = CamerasScreen()
                    cameraScreen.CameraScreen_(cameraList = dataCameras)
                }

                1 -> {
                    val doorScreen = DoorsScreen()
                    doorScreen.DoorsScreen_(doorList = dataDoors)
                }
            }
        }
    }
}
