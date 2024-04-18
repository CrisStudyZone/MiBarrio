package com.example.c17_129_kotlin.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.c17_129_kotlin.R
import com.example.c17_129_kotlin.home.navigation.HomeScreens

@Composable
fun ImageDisplayMain(){
    Card (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.imagen_pantalla_principal),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CustomMenu(
    colorGradient1: Color,
    colorGradient2: Color,
    imageResId: Int,
    text: String,
    onClick : () -> Unit
) {
    val gradientColors =
        Brush.linearGradient(
            0.0f to colorGradient1,
            1.0f to colorGradient2,
            start = Offset.Zero,
            end = Offset.Infinite
        )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .background(gradientColors),

        ) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent), // Color de botón transparente
            shape = RectangleShape, // Forma rectangular sin bordes redondeados
            elevation = null // Sin elevación para evitar sombras
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 3.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                    fontSize = 24.sp,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun MainMenu(
    navController: NavHostController
){
    Column {
        CustomMenu(colorGradient1 = colorResource(id = R.color.Green), colorGradient2 = Color.White, imageResId = R.drawable.imagen_pequena_farmacia, text = "Farmacias de Turno", onClick = {navController.navigate(HomeScreens.PharmacyScreens.route)})
        CustomMenu(colorGradient1 = Color.White, colorGradient2 = colorResource(id = R.color.Red), imageResId = R.drawable.imagen_pequena_bomberos, text = "Bomberos", onClick = {navController.navigate(HomeScreens.FiremanScreens.route)})
        CustomMenu(colorGradient1 = colorResource(id = R.color.BlueLigth), colorGradient2 = Color.White, imageResId = R.drawable.imagen_pequena_policia, text = "Policia", onClick = {navController.navigate(HomeScreens.PoliceScreens.route)})
        CustomMenu(colorGradient1 = Color.White, colorGradient2 = colorResource(id = R.color.Orange), imageResId = R.drawable.imagen_pequena_obras_necesarias, text = "Obras Necesarias", onClick = {})
        CustomMenu(colorGradient1 = colorResource(id = R.color.Blue),colorGradient2 = Color.White, imageResId = R.drawable.imagen_pequena_reparaciones_necesarias, text = "Reparaciones Necesarias", onClick = {})

    }
}

@Composable
fun ScreenMainMenu(
    navController: NavHostController
/*auth: AuthManager*/
){

    //val user = auth.getCurrentUser()

    Column {
        ImageDisplayMain()
        MainMenu(navController = navController)
    }
}
/*
@Composable
fun Color(){
    val mapaDeColores = mapOf(
        1 to Color.White
    )
}

*/