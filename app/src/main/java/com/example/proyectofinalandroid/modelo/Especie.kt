package com.example.proyectofinalandroid.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Especie(@SerialName(value = "nombre") val nombre: String = "",
                   @SerialName(value = "descripcion") val descripcion: String = "",
                   @SerialName(value = "tipo") val tipo: String = "",
                   @SerialName(value = "id") val id: Int = 0
)