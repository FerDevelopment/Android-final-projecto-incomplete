package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.modelo.Especie
import com.example.proyectofinalandroid.ui.ServidorUIState
import com.example.proyectofinalandroid.ui.componentes.CardEspecie

@Composable
fun PrintEspecies(servidorUIState: ServidorUIState, actualizarListaEspecies: () -> Unit) {
   when (servidorUIState) {
      is ServidorUIState.ObtenerExitoEspecies -> {
         PrintCardEspecies(servidorUIState.entidad)
      }

      is ServidorUIState.Cargando -> {

      }

      else -> {
         actualizarListaEspecies()
      }
   }
}

@Composable
fun PrintCardEspecies(entidad: List<Especie>) {
   LazyColumn(modifier = Modifier.padding(10.dp)) {
      items(entidad) { especie ->
         CardEspecie(especie)
      }
   }
}
