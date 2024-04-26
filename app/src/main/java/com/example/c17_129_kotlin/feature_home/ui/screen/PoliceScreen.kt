package com.example.c17_129_kotlin.feature_home.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c17_129_kotlin.R
import com.example.c17_129_kotlin.feature_home.data.InformationPolice
import com.example.c17_129_kotlin.ui.theme.IcPhoneBackground

@Composable
fun PoliceStationImage(){
    Card (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.imagen_estacion_de_policia),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun GoogleMapsMap(){

}

@Composable
fun Title(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Teléfonos",
            fontSize = 28.sp)
    }
}

@Composable
fun InformationCard(information: InformationPolice){
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .clickable { /*TODO: Abrir el telefono*/ }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_phone_foreground),
                contentDescription = null,
                Modifier
                    .background(IcPhoneBackground, shape = CircleShape)
            )
            Column (
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = information.departament,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp)
                Text(text = information.phone,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp)
            }
            Image(
                painter = painterResource(id = R.drawable.ic_phone_foreground),
                contentDescription = null,
                Modifier
                    .background(IcPhoneBackground, shape = CircleShape)
            )
        }
    }
}

@Composable
fun ViewDataList(information: List<InformationPolice>){
    LazyColumn {
        items(information.size){
            InformationCard(information = information[it])
        }
    }
}

val informationData = listOf(
    InformationPolice(departament = "Estacion de Policia", phone = "02374843398"),
    InformationPolice(departament = "Central Policial", phone = "911"),
    InformationPolice(departament = "Emergencias Medicas", phone = "107"),
    InformationPolice(departament = "Asistencia al Suicida", phone = "135"),
    InformationPolice(departament = "Menores Extraviados", phone = "142")
)

@Composable
fun PoliceScreen(){
    Column {
        PoliceStationImage()
        GoogleMapsMap()
        Title()
        ViewDataList(information = informationData)
    }
}
