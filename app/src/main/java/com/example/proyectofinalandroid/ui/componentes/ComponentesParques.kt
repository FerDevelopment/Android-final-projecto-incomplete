package com.example.proyectofinalandroid.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.modelo.Parque

@Composable
fun CardParque(parque: Parque) {
   Card(modifier = Modifier.padding(10.dp)) {
      Column(modifier = Modifier.padding(8.dp)) {
         Text(parque.nombre)
         Text(parque.id.toString())
         Text(parque.extension.toString())/*
                  for (especie in parque.especie) {
                     Text(especie.nombre)
                  }*/
      }
   }
}