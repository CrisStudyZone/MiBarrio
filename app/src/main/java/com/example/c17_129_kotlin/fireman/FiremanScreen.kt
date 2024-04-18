package com.example.c17_129_kotlin.fireman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.c17_129_kotlin.R
import com.example.c17_129_kotlin.fireman.data.Bombero
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

// Datos de ejemplo para demostración
val bomberosEjemplo = listOf(
    Bombero(0, "Juan Pérez", "123-456-7890", "Calle Principal 123"),
    Bombero(1, "María Gómez", "987-654-3210", "Avenida Elm 456"),
    Bombero(2, "María Gómez", "987-654-3210", "Avenida Elm 456")
)

//Aqui van las funciones en orden para mostrarlas en cascada
@Preview
@Composable
fun ImageDisplayMain() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagen_estacion_de_bomberos),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                contentScale = ContentScale.Crop
            )
            //Aqui agregar la parte del mapa de Google Maps
            Text(
                text = "Teléfonos",
                style = MaterialTheme.typography.headlineSmall
            )
            VerBomberos()
        }

    }


    //Implementar funcion de Google Maps
}


@Composable
fun TarjetaBombero(bombero: Bombero) {

    Card(
        modifier = Modifier
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .offset(25.dp)
            ) {
                // Muestra el nombre del bombero
                Text(
                    text = bombero.nombre,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(2.dp))
                // Muestra el número de teléfono
                Text(
                    text = "Teléfono: ${bombero.numeroTelefono}",
                    style = MaterialTheme.typography.bodyMedium
                )
                // Muestra la dirección
                Text(
                    text = "Dirección: ${bombero.direccion}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            IconButton(
                modifier = Modifier
                    .offset((-20).dp),
                onClick = { /*TODO Agregar la funcionalidad de abrir el telefono*/ }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iconocel),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
            }


        }

    }


}


@Composable
fun VistaPreviaTarjetaBombero(bombero: List<Bombero>) {

    LazyColumn {
        items(bombero.size) {
            TarjetaBombero(bombero = bombero[it])
        }
    }
}

@Preview
@Composable
fun VerBomberos() {
    VistaPreviaTarjetaBombero(bombero = bomberosEjemplo)
}
