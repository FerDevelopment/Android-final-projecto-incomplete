package com.example.proyectofinalandroid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyectofinalandroid.Aplicacion
import com.example.proyectofinalandroid.datos.RepositorioInterno
import com.example.proyectofinalandroid.datos.ServidorRepositorio
import com.example.proyectofinalandroid.modelo.ClaseGenerica
import com.example.proyectofinalandroid.modelo.Especie
import com.example.proyectofinalandroid.modelo.Parque
import kotlinx.coroutines.launch

sealed interface ServidorUIState {
   data class ObtenerExitoParques(val entidad: List<Parque>) : ServidorUIState
   data class CrearExito(val entidad: Parque) : ServidorUIState
   data class ActualizarExito(val entidad: Parque) : ServidorUIState
   data class EliminarExito(val id: String) : ServidorUIState
   data class ObtenerExitoEspecies(val entidad: List<Especie>) : ServidorUIState
   object Error : ServidorUIState
   object Cargando : ServidorUIState
}

sealed interface InternoUIState {
   data class ObtenerExito(val entidad: List<ClaseGenerica>) : InternoUIState
   data class CrearExito(val entidad: ClaseGenerica) : InternoUIState
   data class ActualizarExito(val entidad: ClaseGenerica) : InternoUIState
   data class EliminarExito(val id: String) : InternoUIState

   object Error : InternoUIState
   object Cargando : InternoUIState
}

class GenericoViewModel(private val servidorRepositorio: ServidorRepositorio,
                        private val internoRepositorio: RepositorioInterno
) : ViewModel() {
   var servidorUIState: ServidorUIState by mutableStateOf(ServidorUIState.Cargando)
   var internoUIState: InternoUIState by mutableStateOf(
      InternoUIState.Cargando
   )

   fun actualizarListaParques() {
      obtenerListaParques()

   }

   fun actualizarListaEspecies() {
      obtenerListaEspecies()

   }

   private fun obtenerListaEspecies() {
      viewModelScope.launch {
         servidorUIState = ServidorUIState.Cargando
         val listaEpecie = servidorRepositorio.obtenerEspecies()


         servidorUIState = ServidorUIState.ObtenerExitoEspecies(listaEpecie)
      }
   }

   private fun obtenerListaParques() {
      viewModelScope.launch {
         servidorUIState = ServidorUIState.Cargando
         val listaPaques = servidorRepositorio.obtenerParques()


         servidorUIState = ServidorUIState.ObtenerExitoParques(listaPaques)
      }
   }

   companion object {
      val Factory: ViewModelProvider.Factory = viewModelFactory {
         initializer {
            val aplicacion = (this[APPLICATION_KEY] as Aplicacion)
            val servidorRepositorio = aplicacion.contenedor.servidorRepositorio
            val internoRepositorio = aplicacion.contenedor.internoRepositorio

            GenericoViewModel(
               servidorRepositorio = servidorRepositorio, internoRepositorio = internoRepositorio
            )
         }
      }
   }
}