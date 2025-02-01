package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.runtime.Composable
import com.example.proyectofinalandroid.ui.ServidorUIState

@Composable
fun Inicio(
   servidorUIState: ServidorUIState,
   actualizarListaEspecies: () -> Unit,
) {
   when (servidorUIState) {
      is ServidorUIState.ObtenerExitoParques -> {
         PrintCardParques(servidorUIState.entidad)
      }

      is ServidorUIState.Error -> {
         PantallaError()
      }

      else -> {
         actualizarListaEspecies()
      }
   }

}

