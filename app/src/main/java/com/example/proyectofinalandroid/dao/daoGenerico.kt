package com.example.proyectofinalandroid.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectofinalandroid.modelo.UsuarioDB

@Dao
interface ClaseGenericaDao {
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertar(entity: UsuarioDB)

   @Update
   suspend fun actualizar(entity: UsuarioDB)

   @Delete
   suspend fun eliminar(entity: UsuarioDB)

   @Query("SELECT * from UsuarioDB")
   suspend fun ObtenerTodosLosUsuarios(): List<UsuarioDB>
}

