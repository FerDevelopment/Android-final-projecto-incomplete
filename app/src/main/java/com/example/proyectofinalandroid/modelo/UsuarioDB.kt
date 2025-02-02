package com.example.proyectofinalandroid.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuarioDB")
data class UsuarioDB(@PrimaryKey(autoGenerate = true)
                     val id: Int,
                     val nombre: String,
                     val telefono: String,
                     val email: String
)
