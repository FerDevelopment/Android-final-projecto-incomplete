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
import com.example.proyectofinalandroid.modelo.Especie
import com.example.proyectofinalandroid.modelo.Parque
import com.example.proyectofinalandroid.modelo.UsuarioDB
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
   data class ObtenerExito(val entidad: List<UsuarioDB>) : InternoUIState
   data class CrearExito(val entidad: UsuarioDB) : InternoUIState
   data class ActualizarExito(val entidad: UsuarioDB) : InternoUIState
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
   var usuarioDB: UsuarioDB by mutableStateOf(
      UsuarioDB(
         nombre = "Fernando Martinez Burgos",
         telefono = "123 456 789",
         email = "Faaronmb@gmail.com",
         id = 1
      )
   )

   fun actualizarListaParques() {
      obtenerListaParques()

   }

   fun actualizarListaEspecies() {
      obtenerListaEspecies()

   }

   private fun obtenerListaEspecies() {
      viewModelScope.launch {
         try {
            servidorUIState = ServidorUIState.Cargando
            val listaEpecie = servidorRepositorio.obtenerEspecies()


            servidorUIState = ServidorUIState.ObtenerExitoEspecies(listaEpecie)
         } catch (e: Exception) {
            servidorUIState = ServidorUIState.Error
         }
      }
   }

   private fun obtenerListaParques() {
      viewModelScope.launch {
         try {
            servidorUIState = ServidorUIState.Cargando
            val listaPaques = servidorRepositorio.obtenerParques()


            servidorUIState = ServidorUIState.ObtenerExitoParques(listaPaques)
         } catch (e: Exception) {
            servidorUIState = ServidorUIState.Error
         }
      }
   }

   fun actualizarUsuarioDB(_usuarioDB: UsuarioDB) {
      viewModelScope.launch {
         try {
            internoRepositorio.actualizar(_usuarioDB)
            usuarioDB = _usuarioDB

         } catch (_: Exception) {
            obtenerUsuarios()
         }
      }
   }

   init {
      obtenerUsuarios()
   }

   private fun obtenerUsuarios() {
      viewModelScope.launch {
         try {
            val listaUser = internoRepositorio.obtenerTodos()
            if (listaUser.isEmpty()) {
               internoRepositorio.insertar(usuarioDB)
            } else {
               usuarioDB = listaUser.get(0)
            }
         } catch (_: Exception
         ) {
         }
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