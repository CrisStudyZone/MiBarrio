package com.example.c17_129_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.c17_129_kotlin.splashscreen.SplashScreenLocal
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageUrl = "https://media.tenor.com/jB5rZH5NdAoAAAAi/coraje-el-perro-cobarde-coraje.gif"
        //installSplashScreen()
        setContent {
            MyApp(imageUrl = imageUrl)
        }

    }
}

@Composable
fun WelcomeText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WELCOME",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MyApp(imageUrl: String) {
    var showWelcomeScreen by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        showWelcomeScreen = true
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        if (!showWelcomeScreen) {
            //SplashScreen(url = imageUrl)
            SplashScreenLocal()
        } else {
            WelcomeText()
        }
    }
}

