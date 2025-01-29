package com.example.proyectofinalandroid

import android.app.Application
import com.example.proyectofinalandroid.datos.ContenedorApp

class Aplicacion : Application() {
   lateinit var contenedor: ContenedorApp
   override fun onCreate() {
      super.onCreate()
      contenedor = ContenedorApp(this)
   }
}