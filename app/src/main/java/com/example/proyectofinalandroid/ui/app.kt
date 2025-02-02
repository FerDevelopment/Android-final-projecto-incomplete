package com.example.proyectofinalandroid.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.plantillalocal.R
import com.example.proyectofinalandroid.modelo.Ruta
import com.example.proyectofinalandroid.ui.pantallas.ActualizarUsuarioScreen
import com.example.proyectofinalandroid.ui.pantallas.Inicio
import com.example.proyectofinalandroid.ui.pantallas.PrintEspecies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class Pantallas(@StringRes val titulo: Int) {
   Inicio(titulo = R.string.pantalla_inicio),
   Pantalla1(titulo = R.string.pantalla1),
   Pantalla2(titulo = R.string.ajustes),

}

val listaRutas = listOf(
   Ruta(Pantallas.Inicio.titulo, Pantallas.Inicio.name, Icons.Filled.Home, Icons.Outlined.Home),
   Ruta(
      Pantallas.Pantalla1.titulo,
      Pantallas.Pantalla1.name,
      Icons.Filled.Place,
      Icons.Outlined.Place
   )
)
val menu = arrayOf(
   Ruta(
      Pantallas.Pantalla2.titulo,
      Pantallas.Pantalla2.name,
      Icons.Default.Settings,
      Icons.Default.Settings
   )
)

@Composable
fun App(
   navController: NavHostController = rememberNavController(),
   viewModel: GenericoViewModel = viewModel(factory = GenericoViewModel.Factory),
   coroutineScope: CoroutineScope = rememberCoroutineScope(),
   drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {
   var selectedItem by remember { mutableIntStateOf(0) }
   val pilaRetroceso by navController.currentBackStackEntryAsState()
   val pantallaActual = Pantallas.valueOf(
      pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name
   )


   ModalNavigationDrawer(
      drawerState = drawerState,
      drawerContent = {
         ModalDrawerSheet {
            DrawerContent(
               menu = menu,
               pantallaActual = pantallaActual
            ) { ruta ->
               coroutineScope.launch {
                  drawerState.close()
               }

               navController.navigate(ruta)
            }
         }
      },
   ) {
      Scaffold(
         topBar = {
            AppTopBar(
               pantallaActual = pantallaActual,
               drawerState = drawerState
            )
         },
         bottomBar = {
            NavigationBar {
               listaRutas.forEachIndexed { indice, ruta ->
                  NavigationBarItem(
                     icon = {
                        if (selectedItem == indice)
                           Icon(
                              imageVector = ruta.iconoLleno,
                              contentDescription = stringResource(id = ruta.nombre)
                           )
                        else
                           Icon(
                              imageVector = ruta.iconoVacio,
                              contentDescription = stringResource(id = ruta.nombre)
                           )
                     },
                     label = { Text(stringResource(id = ruta.nombre)) },
                     selected = selectedItem == indice,
                     onClick = {
                        selectedItem = indice
                        navController.navigate(ruta.ruta)
                     }
                  )
               }
            }
         },
         modifier = Modifier
            .fillMaxSize()
      ) { innerPadding ->
         NavHost(
            navController = navController,
            startDestination = Pantallas.Inicio.name,
            modifier = Modifier.padding(innerPadding)
         ) {
            // Grafo de las rutas
            composable(route = Pantallas.Inicio.name) {
               Inicio(servidorUIState = viewModel.servidorUIState, actualizarListaEspecies = {
                  viewModel.actualizarListaParques()
               }
               )
            }
            composable(route = Pantallas.Pantalla1.name) {
               PrintEspecies(
                  servidorUIState = viewModel.servidorUIState,
                  actualizarListaEspecies = {
                     viewModel.actualizarListaEspecies()
                  })
            }
            composable(route = Pantallas.Pantalla2.name) {
               ActualizarUsuarioScreen(
                  onActualizarUsuario = { viewModel.actualizarUsuarioDB(it) },
                  onVolver = { navController.popBackStack() },
                  usuario = viewModel.usuarioDB
               )
            }
         }
      }
   }
}

@Composable
private fun DrawerContent(
   menu: Array<Ruta<String>>,
   pantallaActual: Pantallas,
   onMenuClick: (String) -> Unit
) {
   Column(
      modifier = Modifier.fillMaxSize()
   ) {
      Box(
         modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
         contentAlignment = Alignment.Center
      ) {
         Image(
            modifier = Modifier.size(150.dp),
            imageVector = Icons.Filled.AccountCircle,
            contentScale = ContentScale.Crop,
            contentDescription = null
         )
      }
      Spacer(modifier = Modifier.height(12.dp))
      menu.forEach {
         NavigationDrawerItem(
            label = { Text(text = stringResource(id = it.nombre)) },
            icon = { Icon(imageVector = it.iconoLleno, contentDescription = null) },
            selected = it.nombre == pantallaActual.titulo,
            onClick = {
               onMenuClick(it.ruta)
            }
         )
      }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
   pantallaActual: Pantallas,
   drawerState: DrawerState?,
   modifier: Modifier = Modifier
) {
   val coroutineScope = rememberCoroutineScope()

   TopAppBar(
      title = { Text(text = stringResource(id = pantallaActual.titulo)) },
      colors = TopAppBarDefaults.mediumTopAppBarColors(
         containerColor = MaterialTheme.colorScheme.primaryContainer
      ),
      navigationIcon = {
         if (drawerState != null) {
            IconButton(
               onClick = {
                  coroutineScope.launch {
                     drawerState.open()
                  }
               }) {
               Icon(
                  imageVector = Icons.Filled.Menu,
                  contentDescription = stringResource(id = R.string.atras)
               )
            }
         }
      },
      modifier = modifier
   )
}