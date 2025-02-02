package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.plantillalocal.R
import com.example.proyectofinalandroid.modelo.UsuarioDB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onBack: () -> Unit) {
   TopAppBar(title = { Text(title) }, navigationIcon = {
      IconButton(onClick = onBack) {
         Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = stringResource(R.string.atras)
         )
      }
   })
}

@Composable
fun ActualizarUsuarioScreen(usuario: UsuarioDB,
                            onActualizarUsuario: (UsuarioDB) -> Unit,
                            onVolver: () -> Unit
) {
   var nombre by remember { mutableStateOf(usuario.nombre) }
   var telefono by remember { mutableStateOf(usuario.telefono) }
   var email by remember { mutableStateOf(usuario.email) }

   Scaffold(topBar = {
      TopBar(title = stringResource(R.string.actualizar_usuario), onBack = onVolver)
   }) { paddingValues ->
      Column(
         modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(paddingValues)
      ) {
         Column {
            OutlinedTextField(
               value = nombre,
               onValueChange = { nombre = it },
               label = { Text(stringResource(R.string.nombre)) },
               modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
               value = telefono,
               onValueChange = { telefono = it },
               label = { Text(stringResource(R.string.tel_fono)) },
               modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
               value = email,
               onValueChange = { email = it },
               label = { Text(stringResource(R.string.email)) },
               modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
         }

         Column(modifier = Modifier.fillMaxHeight(), Arrangement.Bottom) {
            Button(
               onClick = {
                  val usuarioActualizado = usuario.copy(
                     nombre = nombre, telefono = telefono, email = email
                  )
                  onActualizarUsuario(usuarioActualizado)
                  onVolver()
               }, modifier = Modifier.fillMaxWidth()
            ) {
               Text(stringResource(R.string.actualizar))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
               onClick = onVolver, modifier = Modifier.fillMaxWidth()
            ) {
               Text(stringResource(R.string.cancelar))
            }
         }
      }
   }
}

