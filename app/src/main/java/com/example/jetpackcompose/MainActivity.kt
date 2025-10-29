//package com.example.jetpackcompose
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.Button
//import androidx.compose.material3.CenterAlignedTopAppBar
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
//
//class MainActivity : ComponentActivity() {
//    @OptIn(ExperimentalMaterial3Api::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            JetpackComposeTheme {
//
//                val navController = rememberNavController()
//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//                val currentRoute = navBackStackEntry?.destination?.route
//
//                val currentScreen = (Screen.examples + Screen.Home).find { it.route == currentRoute }
//
//                Scaffold(
//                    topBar = {
//                        CenterAlignedTopAppBar(
//                            title = { Text(currentScreen?.title ?: "Layouts") },
//                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//                                containerColor = MaterialTheme.colorScheme.primaryContainer
//                            ),
//                            navigationIcon = {
//                                if (currentRoute != Screen.Home.route) {
//                                    IconButton(onClick = { navController.navigateUp() }) {
//                                        Icon(
//                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                                            contentDescription = "Volver"
//                                        )
//                                    }
//                                }
//                            }
//                        )
//                    }
//                ) { innerPadding ->
//                    NavHost(
//                        navController = navController,
//                        startDestination = Screen.Home.route,
//                        modifier = Modifier.padding(innerPadding)
//                    ) {
//                        composable(Screen.Home.route) {
//                            HomeScreen(navController = navController)
//                        }
//
//                        composable(Screen.ColumnExample.route) { ColumnExampleScreen() }
//                        composable(Screen.RowExample.route) { RowExampleScreen() }
//                        composable(Screen.BoxExample.route) { BoxExampleScreen() }
//                        composable(Screen.ScaffoldExample.route) { ScaffoldExampleScreen() }
//                        composable(Screen.LazyRowExample.route) { LazyRowExampleScreen() }
//                        composable(Screen.LazyVerticalGridExample.route) { LazyVerticalGridExampleScreen() }
//                    }
//                }
//            }
//        }
//    }
//}
//
///**
// * La pantalla principal.
// * Muestra una lista de botones para navegar a cada ejemplo.
// */
//@Composable
//fun HomeScreen(navController: NavController) {
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(Screen.examples) { screen ->
//            Button(
//                onClick = { navController.navigate(screen.route) },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = screen.title)
//            }
//        }
//    }
//}


package com.example.jetpackcompose

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                Test()
            }
        }
    }
}

@Composable
fun Test() {
    Text(text = "Hola Mundo",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().padding(20.dp))
}

@Preview(showBackground = true)
@Composable
fun SimpleScaffoldPreview() {
    JetpackComposeTheme {
        Test()
    }
}