package com.example.proyectofinalandroid.datos

import com.example.proyectofinalandroid.dao.ClaseGenericaDao
import com.example.proyectofinalandroid.modelo.UsuarioDB

interface RepositorioInterno {
   suspend fun insertar(entidad: UsuarioDB)
   suspend fun actualizar(entidad: UsuarioDB)
   suspend fun eliminar(entidad: UsuarioDB)
   suspend fun obtenerTodos(): List<UsuarioDB>
}

class ConexionGenericaRepositorioInterno(private val claseGenericaDao: ClaseGenericaDao
) : RepositorioInterno {
   override suspend fun insertar(entidad: UsuarioDB) = claseGenericaDao.insertar(entidad)
   override suspend fun actualizar(entidad: UsuarioDB) = claseGenericaDao.actualizar(entidad)
   override suspend fun eliminar(entidad: UsuarioDB) = claseGenericaDao.eliminar(entidad)
   override suspend fun obtenerTodos() = claseGenericaDao.ObtenerTodosLosUsuarios()
}