package com.example.proyectofinalandroid.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ClaseGenerica")
data class ClaseGenerica(
        @PrimaryKey(autoGenerate = true)
        val id : Int
                        )
