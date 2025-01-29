package com.example.proyectofinalandroid.datos

import com.example.proyectofinalandroid.conexion.ServicioAPI
import com.example.proyectofinalandroid.modelo.Especie
import com.example.proyectofinalandroid.modelo.Parque

interface ServidorRepositorio {
   suspend fun obtenerParques(): List<Parque>
   suspend fun insertarParque(entidad: Parque): Parque
   suspend fun actualizarParque(id: Int, entidad: Parque): Parque
   suspend fun eliminarParque(id: Int): Parque

   suspend fun obtenerEspecies(): List<Especie>

   suspend fun insertarEspecie(especie: Especie
   ): Especie

   suspend fun actualizarEspecie(id: Int, especie: Especie
   ): Especie

   suspend fun eliminarEspecie(id: Int
   ): Especie
}

class ConexionRepositorioServidor(private val servicioAPI: ServicioAPI
) : ServidorRepositorio {
   override suspend fun obtenerParques(): List<Parque> = servicioAPI.obtenerParques()

   override suspend fun insertarParque(entidad: Parque): Parque =
           servicioAPI.insertarParque(entidad)

   override suspend fun actualizarParque(id: Int, entidad: Parque
   ): Parque = servicioAPI.actualizarParque(id, entidad)

   override suspend fun eliminarParque(id: Int): Parque = servicioAPI.eliminarParque(id)

   override suspend fun obtenerEspecies(): List<Especie> = servicioAPI.obtenerEspecies()
   override suspend fun insertarEspecie(especie: Especie): Especie =
           servicioAPI.insertarEspecie(especie)

   override suspend fun actualizarEspecie(id: Int, especie: Especie): Especie =
           servicioAPI.actualizarEspecie(id, especie)

   override suspend fun eliminarEspecie(id: Int): Especie = servicioAPI.eliminarEspecie(id)
}