package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.modelo.Parque
import com.example.proyectofinalandroid.ui.componentes.CardParque

@Composable
fun PrintCardParques(listaParques: List<Parque>) {
   LazyColumn(modifier = Modifier.padding(10.dp)) {
      items(listaParques) { parque ->
         CardParque(parque)
      }
   }
}