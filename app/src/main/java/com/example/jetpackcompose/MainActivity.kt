package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                BeautyShopListScreen()
            }
        }
    }
}

data class LocalDeBelleza(
    val nombre: String,
    val especialidad: String,
    val rating: Float,
    val tiempoEspera: String,
)

val listaDeLocalesHardcodeada = listOf(
    LocalDeBelleza(
        nombre = "Salón Divino Glamour",
        especialidad = "Cortes y Coloración Premium",
        rating = 4.9f,
        tiempoEspera = "10 min",
    ),
    LocalDeBelleza(
        nombre = "Barbería Retro",
        especialidad = "Barba y Estilizado Masculino",
        rating = 4.7f,
        tiempoEspera = "20 min",
    ),
    LocalDeBelleza(
        nombre = "Spa de Uñas Cristal",
        especialidad = "Manicure y Pedicure",
        rating = 4.5f,
        tiempoEspera = "5 min",
    ),
    LocalDeBelleza(
        nombre = "Estética Mágica",
        especialidad = "Tratamientos Faciales y Masajes",
        rating = 5.0f,
        tiempoEspera = "30 min",
    )
)

@Composable
fun BeautyShopCard(
    local: LocalDeBelleza,
    onActionClick: (LocalDeBelleza) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { /* Acción  */ }
    ) {
        Column {

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = local.nombre,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = local.especialidad,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${local.rating} (${local.tiempoEspera})",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onActionClick(local) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Reservar")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeautyShopListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Locales de Belleza") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
        ) {
            items(listaDeLocalesHardcodeada) { local ->

                BeautyShopCard(
                    local = local,
                    onActionClick = { localSeleccionado ->
                        println("¡Clic en RESERVAR para ${localSeleccionado.nombre}!")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBeautyShopListScreen() {
    JetpackComposeTheme {
        BeautyShopListScreen()
    }
}