package com.example.c17_129_kotlin.utils

import android.content.Context
import android.net.Uri
import android.preference.PreferenceManager
import com.example.c17_129_kotlin.feature_home.data.PhotoData

object SavePhotoUri {

    fun guardarFotoData(context: Context, fotoData: PhotoData) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString("fotoUri", fotoData.fotoUri?.toString())
        editor.apply()
    }

    fun cargarFotoData(context: Context): PhotoData {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val uriString = sharedPreferences.getString("fotoUri", null)
        val uri = if (uriString != null) Uri.parse(uriString) else null
        return PhotoData(uri)
    }

}