package com.example.myhouseapp

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
import com.example.myhouseapp.Cameras.CameraScreen
import com.example.myhouseapp.Doors.DoorRepository
import com.example.myhouseapp.Doors.DoorsScreen
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDoorsRealm()
        getCameraRealm()
        setContent {
            MainTitle()
        }
    }
}

fun getDoorsRealm(): RealmResults<ItemDataBase> {
    val doorRepository = DoorRepository()
    val config = RealmConfiguration.create(schema = setOf(ItemDataBase::class))
    val realm: Realm = Realm.open(config)
    val doors = realm.query<ItemDataBase>("isDoor == true").find()
    if (doors.isEmpty()) {
        doorRepository.saveDoors()
    }
    return doors
}

fun getCameraRealm(): RealmResults<ItemDataBase> {
    val cameraRepository = CameraRepository()
    val config = RealmConfiguration.create(schema = setOf(ItemDataBase::class))
    val realm: Realm = Realm.open(config)
    val cameras = realm.query<ItemDataBase>("isDoor == false").find()
    if (cameras.isEmpty()) {
        cameraRepository.saveCameras()
    }
    return cameras
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
        when (tabIndex) {
            0 -> CameraScreen(getCameraRealm())
            1 -> DoorsScreen(getDoorsRealm())
        }
    }
}
