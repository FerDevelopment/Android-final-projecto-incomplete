package com.example.proyectofinalandroid.datos

import android.content.Context
import com.example.proyectofinalandroid.conexion.BaseDeDatos
import com.example.proyectofinalandroid.conexion.ServicioAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorAppp {
   val internoRepositorio: RepositorioInterno
   val servidorRepositorio: ServidorRepositorio
}

class ContenedorApp(private val context: Context) : ContenedorAppp {
   private val baseUrl = "http://10.0.2.2:8080/api/"
   private val json = Json { ignoreUnknownKeys = true }
   private val retrofit = Retrofit.Builder()
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .baseUrl(baseUrl).build()
   private val servicioRetrofit: ServicioAPI by lazy {
      retrofit.create(ServicioAPI::class.java)
   }
   override val servidorRepositorio: ServidorRepositorio by lazy {
      ConexionRepositorioServidor(servicioRetrofit)
   }
   override val internoRepositorio: RepositorioInterno by lazy {
      ConexionGenericaRepositorioInterno(BaseDeDatos.obtenerBaseDatos(context).claseGenericaDao())
   }
}