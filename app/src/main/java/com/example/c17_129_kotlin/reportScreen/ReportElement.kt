package com.example.c17_129_kotlin.reportScreen

import androidx.compose.runtime.Composable

data class ReportElement(
    val title: String,
    val hint: @Composable () -> Unit

)
