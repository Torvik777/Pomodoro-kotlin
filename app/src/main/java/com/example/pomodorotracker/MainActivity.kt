package com.example.pomodorotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.foundation.clickable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Timer(0.0f)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Timer(initialProgress: Float) {
    // Створюємо MutableState для прогресу
    var seconds: Int = 60;
    var secondsStr: String = seconds.toString();
    var canGo: Boolean = false;
    val progressTest = remember { mutableStateOf(initialProgress) }
    val secondsTest = remember {mutableStateOf(seconds)}
    val secondsStrTest = remember {mutableStateOf(secondsStr)}
    val canGoTest = remember {mutableStateOf(canGo)}
    secondsStrTest.value = "00:00:${secondsTest.value.toString()}"
    LaunchedEffect(canGoTest.value) {
        while (progressTest.value < 1.0f && canGoTest.value) {
            // Оновлюємо прогрес
            progressTest.value = (60.0f - secondsTest.value.toFloat()) * 0.01666f
            // Чекаємо 1 секунду перед наступним оновленням
            delay(1000L)
        }
    }
    LaunchedEffect(canGoTest.value) {
        while (secondsTest.value > 0 && canGoTest.value) {
            // Оновлюємо прогрес
            secondsTest.value--
            secondsStrTest.value = "00:00:${secondsTest.value.toString()}"
            // Чекаємо 1 секунду перед наступним оновленням
            delay(1000L)
        }
    }

    fun testBtnStart() {
        // Модифікація стану, яка викличе повторну композицію
        canGoTest.value = !canGoTest.value
    }
    fun refreshTimer(){
        progressTest.value = 0.0f
        secondsTest.value = 60
        secondsStrTest.value = "00:00:60"
    }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color
                                (0xFFCC0806),
                            Color(0xFFED1C990),
                        )
                    )
                )
                .fillMaxSize()
                .padding(vertical = 20.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Pomodoro Timer",
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.size(45.dp))

                Text(
                    text = "Pomodoro Timer",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFFFFFFF)
                )

                Spacer(modifier = Modifier.size(45.dp))

                Image(
                    painter = painterResource(id = R.drawable.page_info),
                    contentDescription = "Pomodoro Timer",
                    modifier = Modifier.size(24.dp)
                )

            }

            Spacer(modifier = Modifier.size(80.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Image(
                    painter = painterResource(id = R.drawable.pomodoro_timer),
                    contentDescription = "Pomodoro Timer",
                    modifier = Modifier.size(310.dp)
                )


                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text =  secondsStrTest.value,
                    modifier = Modifier,
                    fontSize = 46.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFFFFF)
                )

                Spacer(modifier = Modifier.size(10.dp))

                LinearProgressIndicator(
                    modifier = Modifier
                        .height(6.dp)
                        .width(340.dp),
                    color = Color(0xFFFFFFFF),
                    trackColor = Color(0xFFF4A2A5),
                    progress = progressTest.value // Встановлюємо прогрес 70% = 0.7f
                )

                Spacer(modifier = Modifier.size(150.dp))

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_notifications),
                        contentDescription = "Pomodoro Timer",
                        modifier = Modifier.size(42.dp)
                    )

                    Spacer(modifier = Modifier.size(60.dp))

                    Image(
                        painter = painterResource(id = R.drawable.icon_play),
                        contentDescription = "Pomodoro Timer",
                        modifier = Modifier.size(100.dp).clickable {
                            testBtnStart()
                        }

                    )


                    Spacer(modifier = Modifier.size(65.dp))

                    Image(
                        painter = painterResource(id = R.drawable.icon_update),
                        contentDescription = "Pomodoro Timer",
                        modifier = Modifier.size(42.dp).clickable {
                            refreshTimer()
                        }
                    )

                }

            }

        }
    LinearProgressIndicator(progress = progressTest.value)
    }



