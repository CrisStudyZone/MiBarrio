package com.example.c17_129_kotlin.feature_home.data

import androidx.compose.runtime.Composable

data class ReportElement(
    val title: String,
    val hint: @Composable () -> Unit

)
