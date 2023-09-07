package com.example.myhouseapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.example.myhouseapp.Cameras.CameraScreen
import com.example.myhouseapp.Doors.DoorRepository
import com.example.myhouseapp.Doors.DoorsScreen
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDoorsRealm()
        setContent {
            MainTitle()
        }
    }
}

fun getDoorsRealm() {
    val doorRepository = DoorRepository()
    val config = RealmConfiguration.create(schema = setOf(ItemRealm::class))
    val realm: Realm = Realm.open(config)
    val data = realm.query<ItemRealm>("isDoor == true").find()
    if(data.isEmpty()) {
        doorRepository.saveDoors()
    }
    for(i in data) {
        i.name?.let { Log.d("my_tag", it) }
    }
}

@Preview
@Composable
fun MainTitle() {
    Column(modifier = Modifier.background(Color.White)) {
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
                    fontWeight = FontWeight(400),
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
            selectedTabIndex = tabIndex,
            containerColor = Color.White
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
                                fontWeight = FontWeight(400),
                                color = Color(0xFF333333),
                                textAlign = TextAlign.Center,
                            )
                        )
                    },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    selectedContentColor = MaterialTheme.colorScheme.secondary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
        when (tabIndex) {
            0 -> CameraScreen()
            1 -> DoorsScreen()
        }
    }
}
