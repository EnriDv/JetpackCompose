package com.example.jetpackcompose
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {

    private val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION, // <-- CAMBIO
        Manifest.permission.RECORD_AUDIO
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                PermissionScreen(
                    permissions = permissionsToRequest,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun PermissionScreen(
    permissions: Array<String>,
    modifier: Modifier = Modifier
) {
    var statusText by remember { mutableStateOf("Esperando acción...") }
    var statusColor by remember { mutableStateOf(Color.Gray) }

    val context = LocalContext.current

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionsMap ->

            // <-- CAMBIO en las variables y lógica
            val locationGranted = permissionsMap[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val audioGranted = permissionsMap[Manifest.permission.RECORD_AUDIO] ?: false

            if (locationGranted && audioGranted) {
                statusText = "¡Permisos de Ubicación y Micrófono CONCEDIDOS!"
                statusColor = Color(0xFF006400) // Verde oscuro
            } else if (locationGranted) {
                statusText = "Permiso de Ubicación concedido, Micrófono denegado."
                statusColor = Color(0xFFFFA500) // Naranja
            } else if (audioGranted) {
                statusText = "Permiso de Micrófono concedido, Ubicación denegada."
                statusColor = Color(0xFFFFA500) // Naranja
            } else {
                statusText = "¡Permisos DENEGADOS!"
                statusColor = Color.Red
            }
        }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            // <-- CAMBIO en la comprobación
            val locationPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            val audioPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)

            if (locationPermission == PackageManager.PERMISSION_GRANTED && audioPermission == PackageManager.PERMISSION_GRANTED) {
                statusText = "Los permisos ya estaban concedidos."
                statusColor = Color.Blue
            } else {
                statusText = "Pidiendo permisos..."
                statusColor = Color.Gray
                requestPermissionLauncher.launch(permissions)
            }
        }) {
            Text("Pedir Permiso (Ubicación + Micrófono)") // <-- CAMBIO en el texto
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = statusText,
            color = statusColor,
            fontSize = 18.sp
        )
    }
}