package com.example.c17_129_kotlin.feature_home.ui.screen

import android.Manifest
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.c17_129_kotlin.R
import com.example.c17_129_kotlin.feature_home.data.ReportElement
import com.example.c17_129_kotlin.feature_home.ui.navigation.HomeScreens
import com.example.c17_129_kotlin.utils.SavePhotoUri.cargarFotoData
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ReporteScreen(
    navController: NavHostController
) {

    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    LaunchedEffect(Unit){
        permissionState.launchPermissionRequest()
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = Color.Transparent,
                    shape = CircleShape
                )
                .border(1.dp, color = Color.Black, shape = CircleShape)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(id = R.drawable.imagen_pequena_reparaciones_necesarias),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center)
                    .size(70.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Reportar un problema",
            color = Color.Gray,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        CollapsingList()

        Spacer(modifier = Modifier.height(20.dp))

        DataReport(onClick = {navController.navigate(HomeScreens.CameraScreen.route)}, context = context)
    }
}

@Composable
fun DataReport(
    onClick: () -> Unit,
    context: Context
) {
    val reportData = listOf(
        ReportElement(title = "¿Donde se ubica el problema", hint = { OpenMap() }),
        ReportElement(title = "Referencias de la ubicación", hint = { OpenTextUbication() }),
        ReportElement(title = "Descripcion del reporte", hint = { OpenTextDescrption() }),
        ReportElement(title = "Capturar imagen ddel reporte", hint = { OpenCamera(onClick = {onClick()}, context = context) })
    )

    LazyColumn {
        items(reportData) {element ->
            ReportElementItem(element)
        }
    }
}

@Composable
fun OpenTextDescrption() {

    var textValue by remember { mutableStateOf("") }
    Column {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(width = 333.dp, height = 119.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
        )
        {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textValue,
                    onValueChange = {
                        // Limitar el texto a 500 caracteres
                        if (it.length <= 500) {
                            textValue = it
                        }
                    },
                    placeholder = { Text("Comentarios") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 2.dp, color = Color.White),
                    singleLine = true,
                )
            }
        }
        // Calcular el número de caracteres escritos y disponibles
        val caracteresEcritos = textValue.length
        val caracteresDisponibles = 500 - caracteresEcritos

        // Mostrar el contador de caracteres debajo del TextField
        Text(
            text = "$caracteresEcritos/$caracteresDisponibles",
            style = MaterialTheme.typography.bodyLarge,
            color = if (caracteresDisponibles >= 0) {
                Color.Gray // Puedes ajustar el color según tus preferencias
            } else {
                Color.Red // Puedes ajustar el color según tus preferencias
            },
            modifier = Modifier
                .padding(top = 4.dp)
                .align(Alignment.End)
        )
    }
}

@Composable
fun OpenCamera(
    context: Context,
    onClick: () -> Unit
) {
    val fotoData = remember { mutableStateOf(cargarFotoData(context)) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(width = 333.dp, height = 119.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(start = 14.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(width = 88.dp, height = 92.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            if (fotoData.value.fotoUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(fotoData.value.fotoUri!!),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp, 30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OpenTextUbication() {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(width = 333.dp, height = 119.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "Calle, Barrio, indicaciones que considere necesarias",
                onValueChange = { },
                placeholder = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, color = Color.White),
                singleLine = true
            )
        }
    }
}


@Composable
fun OpenMap() {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(width = 333.dp, height = 119.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                painter = painterResource(id = R.drawable.baseline_place_24),
                contentDescription = "Place Icon",
                )
            OutlinedTextField(
                value = "Toca para agregar la ubicacion",
                onValueChange = {  },
                placeholder = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, color = Color.White),
                singleLine = true
            )
        }
    }
}

@Composable
fun ReportElementItem(element: ReportElement) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)) {
        Text(
            text = element.title,
            style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Box (
            modifier = Modifier
                .clickable {
                // TODO: Abre Google Maps al hacer clic en el icono
            }
        ){
            element.hint()
        }
    }
}

@Composable
fun CollapsingList() {
    val items = listOf("Luminarias", "Bache", "Poda", "Peligro", "Perdida de Gas", "Filtracion de agua", "Reparacion Cloaca")
    val expandedState = remember { mutableStateOf(false) }

    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                    .size(width = 200.dp, height = 45.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = if (expandedState.value) "Cerrar" else "Tipo de Reporte",
                        modifier = Modifier
                            .clickable { expandedState.value = !expandedState.value },
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.baseline_expand_more_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { expandedState.value = !expandedState.value })
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        if (expandedState.value) {
            items(items.size) { index ->
                CollapsingSection(
                    header = {
                        Text(text = items[index])
                    },
                    content = {
                        // Contenido de cada opción
                    },
                    expanded = expandedState.value
                )
            }
        }
    }
}

@Composable
fun CollapsingSection(
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
    expanded: Boolean
) {
    Column {
        if (expanded) {
            header()
            content()
        }
    }
}

@Preview
@Composable
fun ReporteScreenPreview() {
    //ReporteScreen()
}