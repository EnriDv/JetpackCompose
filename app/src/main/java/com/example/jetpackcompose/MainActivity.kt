import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()

    data class Error(val message: String) : UiState<Nothing>()
}

@Composable
fun <T> StatefulContentManager(
    state: UiState<T>,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
    onSuccess: @Composable (data: T) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = state.message,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onRetry) {
                        Text("Reintentar")
                    }
                }
            }

            is UiState.Success -> {
                onSuccess(state.data)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateManagerExampleScreen() {

    var currentState: UiState<String> by remember { mutableStateOf(UiState.Loading) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Demo Gestor de Estado") }) }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { currentState = UiState.Loading }) { Text("Cargando") }
                Button(onClick = { currentState = UiState.Success("¡Datos cargados con éxito!") }) { Text("Éxito") }
                Button(onClick = { currentState = UiState.Error("No se pudo conectar al servidor.") }) { Text("Error") }
            }

            Divider(modifier = Modifier.padding(vertical = 16.dp))

            StatefulContentManager(
                state = currentState,
                onRetry = {
                    currentState = UiState.Loading
                },
                onSuccess = { dataString ->
                    SuccessContentView(data = dataString)
                }
            )
        }
    }
}

@Composable
fun SuccessContentView(data: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Contenido de Éxito",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = data,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF006400)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStateManagerExample() {
    JetpackComposeTheme {
        StateManagerExampleScreen()
    }
}