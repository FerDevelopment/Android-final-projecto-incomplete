package com.example.proyectofinalandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectofinalandroid.ui.App
import com.example.proyectofinalandroid.ui.theme.ProyectoFinalAndroidTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         ProyectoFinalAndroidTheme {
            App()
         }
      }
   }
}


