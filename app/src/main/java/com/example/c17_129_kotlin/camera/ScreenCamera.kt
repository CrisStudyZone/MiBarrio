package com.example.c17_129_kotlin.camera

import android.net.Uri
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@Composable
fun CameraScreen(
    navController: NavHostController
){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val fotoUri = remember { mutableStateOf<Uri?>(null) }
    val lifecycle = LocalLifecycleOwner.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "ATRÁS")
                }

                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            val executor = ContextCompat.getMainExecutor(context)
                            takePicture(
                                cameraController,
                                executor,
                                onSuccess = { uri ->
                                    fotoUri.value = uri
                                }
                            )
                        }
                    }
                ) {
                    Text(text = "¡Cámara!")
                }
            }
        }
    ){
        if (fotoUri.value != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(fotoUri.value!!),
                    contentDescription = null,
                    modifier = Modifier
                        .heightIn(300.dp)
                        .aspectRatio(1f)

                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FloatingActionButton(
                        onClick = {
                            fotoUri.value = null
                        }
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Cancelar")
                    }
                    FloatingActionButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Aceptar")
                    }
                }
            }
        }else{
            CamaraCompose(cameraController, lifecycle, modifier = Modifier.padding(it))
        }

    }
}