package com.example.c17_129_kotlin.pharmacy

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c17_129_kotlin.R
import com.example.c17_129_kotlin.pharmacy.data.PharmacyData


@Composable
fun PharmacyScreen(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ){
        Row (
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
        ){
            Image(
                painter = painterResource(id = R.drawable.imagen_pequena_farmacia),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = "Farmacias de turno",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterVertically)
            )
            Image(
                painter = painterResource(id = R.drawable.imagen_pequena_farmacia),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }
        val pharmacyDataOpen = PharmacyData(pharmacyName = "Farmacia XYZ", pharmacyAddress = "Calle Principal 123", pharmacyPhone = "123-456-7890", open = true)
        val pharmacyDataOpen1 = PharmacyData(pharmacyName = "Farmacia", pharmacyAddress = "Calle Principal 123", pharmacyPhone = "123-456-7890", open = true)
        val pharmacyDataClose = PharmacyData(pharmacyName = "Farmacia hyr", pharmacyAddress = "Calle Principal 123", pharmacyPhone = "123-456-7890", open = false)
        val pharmacyList = listOf(pharmacyDataClose,pharmacyDataOpen,pharmacyDataOpen1)

        val checkPharmacy: @Composable (List<PharmacyData>) -> Unit = { list ->
            for (pharmacy in list) {
                if (pharmacy.open == true) {
                    CustomFarmaciasDeTurno(pharmacyData = pharmacy)
                }
            }
        }
        checkPharmacy(pharmacyList)
    }
}

@Composable
fun CustomFarmaciasDeTurno(pharmacyData: PharmacyData){
    Spacer(modifier = Modifier.padding(5.dp))

    val gradientColors =
        Brush.linearGradient(
            0.0f to colorResource(id = R.color.Green),
            1.0f to Color.White,
            start = Offset.Zero,
            end = Offset.Infinite
        )

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .background(gradientColors)
            .clickable { /*TODO: Abrir googleMaps en la ubicacion correcta*/ }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location_foreground),
                contentDescription = null,
            )
            Column (
                modifier = Modifier
            ) {
                Text(
                    text = pharmacyData.pharmacyName,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = pharmacyData.pharmacyAddress,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable { /*TODO: Abrir googleMaps en la ubicacion correcta*/ }
                )
                Text(
                    text = pharmacyData.pharmacyPhone,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable { /*Call(numeroTelefono = pharmacy.pharmacyPhone)*/ } //No me reconoce la funcion call como @Composable
                )
            }
        }
    }
}

//Funcion para llamar cuando oprimo el numero de telefono de la farmacia
//No la pude probar
@Composable
fun Call(numeroTelefono: String) {
    val openDialerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$numeroTelefono")
    }
    val context = LocalContext.current
    val packageManager = context.packageManager
    if (intent.resolveActivity(packageManager) != null) {
        openDialerLauncher.launch(intent)
    } else {
        Toast.makeText(context, "No se puede abrir el marcador", Toast.LENGTH_SHORT).show()
    }
}

//Aqui trate de hacer la navegacion a googleMaps pero no salio porque no reconocia la funcion como composable
//No se si funciona correctamente porque no lo pude probar
@Composable
fun OpenGoogleMaps(){
    //Ubicacion en el mapa
    val latitude = "37.7749"
    val longitude = "-122.4194"
    val label = "Ubicación de ejemplo"

    val context = LocalContext.current

    OpenUrlInBrowser(context = context, url = "http://maps.google.com/maps?q=loc:$latitude,$longitude($label)")

}

@Composable
fun OpenUrlInBrowser(context: Context, url: String) {
    val openUrlLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // No es necesario hacer nada después de abrir el navegador
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        openUrlLauncher.launch(intent)
    } catch (e: ActivityNotFoundException) {
        // Manejar la excepción si no se encuentra una actividad para manejar la acción de ver la URL
    }
}

@Preview(showBackground = true)
@Composable
fun PharmacyScreenPreview() {

        PharmacyScreen()

}

