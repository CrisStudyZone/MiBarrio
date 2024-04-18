package com.example.c17_129_kotlin.login

import android.content.Context
import android.opengl.Visibility
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c17_129_kotlin.R
import com.example.c17_129_kotlin.ui.theme.ButtonsLogin
import com.example.c17_129_kotlin.ui.theme.ColorBlue
import com.example.c17_129_kotlin.ui.theme.ColorBlueGreen
import com.example.c17_129_kotlin.ui.theme.ColorPurple
import com.example.c17_129_kotlin.ui.theme.TextLogin
import com.example.c17_129_kotlin.utils.AuthManager
import com.example.c17_129_kotlin.utils.AuthRes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

@Composable
fun LogScreen(auth: AuthManager){

    val context = LocalContext.current

    Card (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ){
        Spacer(modifier = Modifier.padding(30.dp))
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(171.dp, 128.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null)
        Spacer(modifier = Modifier.padding(30.dp))


        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            CustomButton(image = R.drawable.icono_google, text = "Login With", onClick = { LoginWithGoogle<GoogleSignInAccount>(context = context, AuthManager(context = context))})
            Spacer(modifier = Modifier.padding(20.dp))
            CustomButton(image = R.drawable.icono_facebook, text = "Login With", onClick = { LoginWithFacebook() })
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Separator(text = "OR")

        Spacer(modifier = Modifier.padding(10.dp))

        LoginWithMailAndPassword(context = context, auth = auth)

        Spacer(modifier = Modifier.padding(10.dp))

        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ){
            
            ButtonForgotPassword()

            Spacer(modifier = Modifier.padding(10.dp))

            ButtonResetPassword()
            
        }

        Spacer(modifier = Modifier.padding(30.dp))

        ButtonSingUp()

    }
}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginWithMailAndPassword(context: Context, auth: AuthManager){

    val scope = rememberCoroutineScope()

    // Estados para el email y la contraseña
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Función para manejar el clic del botón de registro
    val handleRegisterClick: () -> Unit = {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            // Realizar el registro con correo electrónico y contraseña
            scope.launch {
                when (val registerResult = auth.singInWithEmailAndPassword(email, password)) {
                    is AuthRes.Success -> {
                        // Registro exitoso, realizar acciones necesarias (navegación, etc.)
                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        // Navegar a la siguiente pantalla (por ejemplo, la pantalla de inicio)
                    }
                    is AuthRes.Error -> {
                        // Manejar el caso en que el registro falla
                        Toast.makeText(context, "Error: ${registerResult.errorMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            // Mostrar un mensaje si el email o la contraseña están vacíos
            Toast.makeText(context, "Por favor, ingresa el email y la contraseña", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var textEmail by remember { mutableStateOf("Email ID / Phone No.") }
        var textPassword by remember { mutableStateOf("Password") }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }


            val customTextFieldColors = TextFieldDefaults.colors(
            selectionColors = TextSelectionColors(backgroundColor = ButtonsLogin, handleColor = ButtonsLogin) // Aquí puedes especificar el color de fondo deseado
        )

        OutlinedTextField(
            value = textEmail,
            onValueChange = { email = it},
            modifier = Modifier
                .background(
                    color = ButtonsLogin.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = ButtonsLogin,
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = textPassword,
            onValueChange = { email = it},
            modifier = Modifier
                .background(
                    color = ButtonsLogin.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = ButtonsLogin,
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                ) {
                    Image(
                        painter = VisibilityPassword(passwordVisible),
                        contentDescription = if (passwordVisible) "Hide Password" else "Show Password"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContainedButtonExample()
    }
}

@Composable
fun ContainedButtonExample() {
    val WIDTH1 = 150.dp
    val WIDTH2 = 200.dp
    val WIDTH3 = 300.dp

    var width by remember { mutableStateOf(WIDTH1) }



    Button(
        onClick = {
            width = when (width) {
                WIDTH1 -> WIDTH2
                WIDTH2 -> WIDTH3
                else -> WIDTH1
            }
            /*TODO terminar Auth*/
        },
        modifier = Modifier.width(width).size(280.dp, 57.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),

    ) {
        val gradient =
            Brush.verticalGradient(
                listOf(ColorBlueGreen, ColorBlue, ColorPurple),
                startY = 0.0f,
                endY = 100.0f,
            )
        Box(
            modifier = Modifier
                .background(gradient)
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    width = when (width) {
                        WIDTH1 -> WIDTH2
                        WIDTH2 -> WIDTH3
                        else -> WIDTH1
                    }
                },
            contentAlignment = Alignment.Center,

        ) {
            Text("LOGIN")
        }
    }
}
@Composable
fun VisibilityPassword(passwordVisible: Boolean): Painter {
    return if (passwordVisible) {
         painterResource(id = R.drawable.icon_visibility)
    }
    else painterResource(id = R.drawable.icon_visibility_off)
}

@Composable
fun <GoogleSignInAccount> LoginWithGoogle(context: Context, auth: AuthManager){

    val scope = rememberCoroutineScope()

    //Abro un listado de dialogo con todas las cuentas que hayan iniciado anteriormente con este movil esto debe ir en el loginScreen
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()) { result ->
        when(val account = auth.handleSingInResult(GoogleSignIn.getSignedInAccountFromIntent(result.data))){
            is AuthRes.Success -> {
                val credential = GoogleAuthProvider.getCredential(account?.data?.idToken, null)
                scope.launch {
                    val fireUser = auth.signInWithGoogleCredential(credential)
                    if (fireUser != null){
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                        //aca va la navegacion al home
                        /*                        navigation.navigate(Routes.Home.route){
                            popUpTo(Routes.Login.route){
                                inclusive = true
                            }
                        }*/
                    }
                }
            }
            is AuthRes.Error -> {
                //analytics.logError("Error SingIn: ${account.errorMessage}") activar cuando este listo este log de analytics
                Toast.makeText(context, "Error: ${account.errorMessage}", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun LoginWithFacebook(){

}

@Composable
fun CustomButton(image: Int, text: String, onClick: @Composable () -> Unit){
    Box(
        modifier = Modifier
            .size(144.dp, 36.dp)
            .background(Color.Transparent)
            .border(width = 2.dp, color = ButtonsLogin, shape = RoundedCornerShape(8.dp))
            .clickable { onClick },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                color = TextLogin,
                modifier = Modifier
                    .padding(end = 8.dp) // Espacio entre el texto y la imagen
            )
            Icon(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun Separator(text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            color = ButtonsLogin,
            thickness = 2.dp,
            modifier = Modifier.weight(1f)
        ) // La línea se extiende para llenar el espacio
        Text(
            text = text,
            color = ButtonsLogin,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Divider(
            color = ButtonsLogin,
            thickness = 2.dp,
            modifier = Modifier.weight(1f)
        ) // Otra línea para completar el espacio restante
    }
}

@Composable
fun ButtonForgotPassword(){
    Box(
        modifier = Modifier
            .size(144.dp, 36.dp)
            .background(Color.Transparent)
            .clickable { /* TODO */ },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Forgot Password?",
            color = Color.Black,
            fontSize = 14.sp)
    }
}

@Composable
fun ButtonResetPassword(){
    Box(
        modifier = Modifier
            .size(144.dp, 36.dp)
            .background(Color.Transparent)
            .border(width = 2.dp, color = ButtonsLogin, shape = RoundedCornerShape(8.dp))
            .clickable { /*TODO vincular con resetPassword de authManager*/ },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Reset Password",
            color = TextLogin,
            fontSize = 14.sp)
    }
}

@Composable
fun ButtonSingUp(){
    Box(
        modifier = Modifier
            .size(256.dp, 36.dp)
            .background(Color.Transparent)
            .border(width = 2.dp, color = ButtonsLogin, shape = RoundedCornerShape(8.dp))
            .clickable { /*TODO crear pantalla de registro*/ },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Singup",
            color = TextLogin)
    }
}

@Preview
@Composable
fun LogScreenPreview() {
    // Aquí puedes inicializar AuthManager con un contexto de prueba
    val authManager = AuthManager(context = LocalContext.current)

    // Llama a LogScreen y pasa la instancia de AuthManager
    LogScreen(auth = authManager)
}
