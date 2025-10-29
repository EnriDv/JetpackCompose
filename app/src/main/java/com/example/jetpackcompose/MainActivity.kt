package com.example.jetpackcompose // Asegúrate que sea tu package name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme // Cambia esto por tu tema
import com.example.tuproyecto.Screen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme { // Asegúrate que este sea el nombre de tu tema

                val navController = rememberNavController()
                // Observa la ruta actual para saber qué título mostrar
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Buscamos el título correspondiente a la ruta actual
                val currentScreen = (Screen.examples + Screen.Home).find { it.route == currentRoute }

                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(currentScreen?.title ?: "Layouts") },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            ),
                            navigationIcon = {
                                // Muestra el botón de "atrás" si no estamos en Home
                                if (currentRoute != Screen.Home.route) {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Volver"
                                        )
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    // El NavHost es el que se encarga de mostrar la página correcta
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Ruta para la pantalla principal
                        composable(Screen.Home.route) {
                            HomeScreen(navController = navController)
                        }

                        // Registramos todas las rutas de los ejemplos
                        // Cada una llama a su Composable correspondiente
                        composable(Screen.ColumnExample.route) { ColumnExampleScreen() }
                        composable(Screen.RowExample.route) { RowExampleScreen() }
                        composable(Screen.BoxExample.route) { BoxExampleScreen() }
                        composable(Screen.SpacerExample.route) { SpacerExampleScreen() }
                        composable(Screen.SurfaceExample.route) { SurfaceExampleScreen() }
                        composable(Screen.ScaffoldExample.route) { ScaffoldExampleScreen() }
                        composable(Screen.ConstraintLayoutExample.route) { ConstraintLayoutExampleScreen() }
                        composable(Screen.LazyColumnExample.route) { LazyColumnExampleScreen() }
                        composable(Screen.LazyRowExample.route) { LazyRowExampleScreen() }
                        composable(Screen.LazyVerticalGridExample.route) { LazyVerticalGridExampleScreen() }
                        composable(Screen.FlowRowExample.route) { FlowRowExampleScreen() }
                        composable(Screen.BoxWithConstraintsExample.route) { BoxWithConstraintsExampleScreen() }
                    }
                }
            }
        }
    }
}

/**
 * La pantalla principal.
 * Muestra una lista de botones para navegar a cada ejemplo.
 */
@Composable
fun HomeScreen(navController: NavController) {
    // Usamos LazyColumn para que la lista sea "scrollable"
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre botones
    ) {
        items(Screen.examples) { screen ->
            Button(
                onClick = { navController.navigate(screen.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = screen.title)
            }
        }
    }
}