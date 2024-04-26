package com.example.c17_129_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.c17_129_kotlin.feature_home.ui.navigation.HomeNavigation
import com.example.c17_129_kotlin.utils.AuthManager
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics

class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analytics = Firebase.analytics
        setContent {
            MyApp()
        }

    }
}

@Composable
fun MyApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        HomeNavigation()
    }
}