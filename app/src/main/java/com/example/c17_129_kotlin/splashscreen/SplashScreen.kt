package com.example.c17_129_kotlin.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.c17_129_kotlin.R

@Composable
fun SplashScreenUrl(url: String) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .decoderFactory(GifDecoder.Factory())
        .crossfade(true)
        .build()

    AsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = imageRequest,
        contentDescription = "GIF"
    )
}

@Composable
fun SplashScreenLocal() {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components { add(GifDecoder.Factory()) }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = R.drawable.corajesplash) // ACA SE CAMBIA EL GIF
                .build(),
            imageLoader = imageLoader
        ),
        contentDescription = "",
    )
}