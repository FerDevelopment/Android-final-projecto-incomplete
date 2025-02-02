package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.plantillalocal.R

@Composable
fun PantallaError() {
   Column(
      modifier = Modifier.fillMaxSize(),
      Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      Text(stringResource(R.string.error_manito_d))
   }
}