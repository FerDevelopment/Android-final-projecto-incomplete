package com.example.proyectofinalandroid.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.plantillalocal.R
import com.example.proyectofinalandroid.modelo.Especie

@Composable
fun CardEspecie(entidad: Especie) {
   Card(
      modifier = Modifier
         .fillMaxWidth()
         .padding(10.dp)
   ) {
      Column(modifier = Modifier.padding(8.dp)) {
         Text(stringResource(R.string.nombre2) + entidad.nombre)

         Text(stringResource(R.string.descripcion) + ": " + entidad.descripcion)
      }
   }
}