package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.proyectofinalandroid.modelo.Parque
import com.example.proyectofinalandroid.ui.componentes.CardParque

@Composable
fun Inicio(listaParques: List<Parque>, actualizarListaEspecies: () -> Unit) {
   if (listaParques.isEmpty()) {
      actualizarListaEspecies()
   }
   LazyColumn {
      items(listaParques) { parque ->
         CardParque(parque)
      }
   }

}