package com.example.c17_129_kotlin.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.example.c17_129_kotlin.R
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

sealed class AuthRes<out T>{
    data class Success<T>(val data: T): AuthRes<T>()
    data class Error(val errorMessage: String): AuthRes<Nothing>()
}

class AuthManager (private val context: Context){
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private val singInClient = Identity.getSignInClient(context)

    //Ingreso anonimo que no esta disponible en este caso
    suspend fun singInAnonymously(): AuthRes<FirebaseUser> {
        return try {
            val result = auth.signInAnonymously().await()
            AuthRes.Success(result.user ?: throw  Exception("Error al iniciar sesion"))
        } catch (e: Exception) {
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }

    //Funcion para crear un usuario con email y password
    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser?> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            AuthRes.Success(authResult.user)
        } catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al crear el usuario")
        }
    }

    //Funcion para ingresar un usuario registrado con email y password
    suspend fun singInWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser?> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            AuthRes.Success(authResult.user)
        } catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }

    //Funcion para autenticacion con google
    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    //Esta funcion si va aca, ojo que debo hacer bien el llamado cuando pase la variable anterios a la carpeta de login
    fun handleSingInResult(task: Task<GoogleSignInAccount>): AuthRes<GoogleSignInAccount>? {
        return try {
            val account = task.getResult(ApiException::class.java)
            AuthRes.Success(account)
        } catch (e: ApiException) {
            AuthRes.Error(e.message ?: "Google sing-in failed.")
        }
    }

    //Funcion que recibe la credencial y devuelve el usuario
    suspend fun signInWithGoogleCredential(credential: AuthCredential): AuthRes<FirebaseUser>? {
        return try {
            val firebaseUser = auth.signInWithCredential(credential).await()
            firebaseUser.user?.let {
                AuthRes.Success(it)
            } ?: throw Exception("Sing in with Google failed.")
        } catch (e: Exception){
            AuthRes.Error(e.message ?: "Sign in with Google failed.")
        }
    }

    fun signInWithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>) {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    //Funcion para resetear contraseña
    suspend fun resetPassword(email: String): AuthRes<Unit>{
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthRes.Success(Unit)
        } catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al restablecer la contraseña")
        }
    }

    //Funcion para cerrar sesión
    fun singOut(){
        auth.signOut()
        singInClient.signOut()
    }

    //Funcion para recuperar el usuario
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

}