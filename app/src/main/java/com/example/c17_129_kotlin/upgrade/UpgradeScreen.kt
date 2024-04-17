package com.example.c17_129_kotlin.upgrade

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.c17_129_kotlin.R


val imageItemList = listOf(
    ImageItemUpgrade(name = "School", imageResourceId = R.drawable.imagen_mejoras_en_escuelas),
    ImageItemUpgrade(name = "Hospital", imageResourceId = R.drawable.imagen_mejoras_hospital),
    ImageItemUpgrade(name = "Play&Park", imageResourceId = R.drawable.imagen_mejoras_en_plazas_y_parques),
    ImageItemUpgrade(name = "QualityOfLife", imageResourceId = R.drawable.imagen_mejoras_calidad_de_vida),
    ImageItemUpgrade(name = "Street", imageResourceId = R.drawable.imagen_mejoras_en_calles)

)

@Composable
fun UpgradeScreen(){
    CustomUpgradeScreen(imageItemList = imageItemList)
}
@Composable
fun CustomUpgradeScreen(imageItemList: List<ImageItemUpgrade>){
    LazyColumn {
        items(imageItemList) { imageResourceID ->
            UploadButtonImage(imageUpload = imageResourceID)
        }
    }
}

@Composable
fun UploadButtonImage(imageUpload: ImageItemUpgrade){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            /*.clickable { TODO("Agregar navegavilidad a cada pantalla de cada mejora") }*/){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f/3.5f),
            painter = painterResource(id = imageUpload.imageResourceId),
            contentDescription = null)
    }
}



