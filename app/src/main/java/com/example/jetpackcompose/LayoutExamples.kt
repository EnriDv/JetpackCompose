package com.example.jetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun DemoBox(text: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(100.dp)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

// --- 1. Column ---
/**
 * `Column` apila sus hijos verticalmente, uno encima del otro.
 * Es como un `LinearLayout` vertical.
 * - `verticalArrangement` controla el espacio vertical (ej. `SpaceEvenly`, `SpaceBetween`).
 * - `horizontalAlignment` controla la alineación horizontal (ej. `CenterHorizontally`).
 */
@Preview(showBackground = true)
@Composable
fun ColumnExampleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DemoBox(text = "1", color = Color.Red)
        DemoBox(text = "2", color = Color.Green)
        DemoBox(text = "3", color = Color.Blue)
    }
}

// --- 2. Row ---
/**
 * `Row` coloca a sus hijos horizontalmente, uno al lado del otro.
 * Es como un `LinearLayout` horizontal.
 * - `horizontalArrangement` controla el espacio horizontal (ej. `SpaceAround`).
 * - `verticalAlignment` controla la alineación vertical (ej. `CenterVertically`).
 */
@Preview(showBackground = true)
@Composable
fun RowExampleScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Damos altura para ver la alineación vertical
            .padding(16.dp)
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DemoBox(text = "A", color = Color.Red)
        DemoBox(text = "B", color = Color.Green)
        DemoBox(text = "C", color = Color.Blue)
    }
}

// --- 3. Box ---
/**
 * `Box` apila a sus hijos uno encima del otro, como `FrameLayout`.
 * Es perfecto para superponer elementos.
 * - `contentAlignment` controla dónde se posicionan los hijos dentro del `Box`
 * (por defecto es `TopStart`).
 * - Puedes usar `Modifier.align()` en un hijo para posicionarlo individualmente.
 */
@Preview(showBackground = true)
@Composable
fun BoxExampleScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        DemoBox(text = "Fondo", color = Color.Gray, modifier = Modifier.size(300.dp))
        DemoBox(text = "Medio", color = Color.Blue, modifier = Modifier.size(200.dp))
        DemoBox(text = "Frente", color = Color.Red, modifier = Modifier.size(100.dp))

        // Este hijo ignora el contentAlignment y se va a TopEnd
        Text(
            text = "¡Esquina!",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(Color.Yellow)
                .padding(8.dp)
        )
    }
}

// --- 4. Spacer ---
/**
 * `Spacer` es literalmente un espacio vacío.
 * Es la forma moderna de agregar márgenes o espacios fijos entre elementos
 * dentro de un `Row` o `Column`.
 */
@Preview(showBackground = true)
@Composable
fun SpacerExampleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Elemento 1")

        // Un espacio de 32dp
        Spacer(modifier = Modifier.height(32.dp))

        Text("Elemento 2")

        Row {
            Text("A")
            // Un espacio de 16dp
            Spacer(modifier = Modifier.width(16.dp))
            Text("B")
        }
    }
}

// --- 5. Surface ---
/**
 * `Surface` es un contenedor que dibuja un fondo, maneja la elevación (sombras),
 * y recorta su contenido (ej. esquinas redondeadas).
 * Es la base de Cards, Buttons, etc.
 */
@Preview(showBackground = true)
@Composable
fun SurfaceExampleScreen() {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .size(200.dp),
            color = MaterialTheme.colorScheme.surfaceVariant, // Color de fondo
            shape = RoundedCornerShape(20.dp), // Esquinas redondeadas
            shadowElevation = 10.dp // Sombra
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Contenido del Surface")
            }
        }
    }
}

// --- 6. Scaffold ---
/**
 * `Scaffold` implementa la estructura básica de Material Design.
 * Te da "ranuras" (slots) para poner:
 * - `topBar`: Barra superior
 * - `bottomBar`: Barra inferior
 * - `floatingActionButton` (FAB)
 * - `snackbarHost`: Para mostrar mensajes
 * - El contenido principal (en el lambda `content`).
 * (Nota: Nuestra app ya usa un Scaffold. Esto es un ejemplo de cómo usarlo).
 */
@Preview(showBackground = true)
@Composable
fun ScaffoldExampleScreen() {
    // Scaffold anidado solo para demostración
    Scaffold(
        topBar = {
            Surface(shadowElevation = 4.dp) {
                Text(
                    "TopBar de Ejemplo",
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(onClick = { }) { Icon(Icons.Default.Home, "") }
                    IconButton(onClick = { }) { Icon(Icons.Default.Edit, "") }
                    IconButton(onClick = { }) { Icon(Icons.Default.Check, "") }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Este es el contenido principal del Scaffold",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


// --- 7. ConstraintLayout ---
/**
 * `ConstraintLayout` permite posicionar elementos en relación con otros
 * o con el contenedor padre.
 * Es muy potente para layouts complejos y planos (sin anidación).
 * - `createRefs()` crea referencias para los elementos.
 * - `Modifier.constrainAs(ref)` aplica las restricciones.
 * - `linkTo()` define las restricciones (ej. `top.linkTo(parent.top)`).
 */
@Preview(showBackground = true)
@Composable
fun ConstraintLayoutExampleScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // 1. Crea las referencias
        val (button1, text1, button2) = createRefs()

        // 2. Asigna las referencias y restricciones
        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button1) {
                // Centrado horizontalmente en el padre
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                // Pegado al Top
                top.linkTo(parent.top)
            }
        ) {
            Text("Botón 1 (Centrado)")
        }

        Text(
            "Texto debajo del Botón 1",
            Modifier.constrainAs(text1) {
                // Centrado horizontal con button1
                start.linkTo(button1.start)
                end.linkTo(button1.end)
                // 8dp debajo del fondo de button1
                top.linkTo(button1.bottom, margin = 8.dp)
            }
        )

        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button2) {
                // Pegado al fondo y al final del padre
                bottom.linkTo(parent.bottom, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            }
        ) {
            Text("Botón 2 (Esquina)")
        }
    }
}

// --- 8. LazyColumn ---
/**
 * `LazyColumn` es el equivalente a `RecyclerView` vertical.
 * Es "Lazy" (perezoso) porque solo renderiza los elementos que son
 * visibles en pantalla. Es esencial para listas largas.
 */
@Preview(showBackground = true)
@Composable
fun LazyColumnExampleScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre items
    ) {
        item {
            Text("Cabecera de la Lista", style = MaterialTheme.typography.titleLarge)
        }

        // Muestra 100 items
        items(100) { index ->
            Text(
                text = "Item número $index",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
            )
        }
    }
}

// --- 9. LazyRow ---
/**
 * `LazyRow` es el equivalente a `RecyclerView` horizontal.
 * Igual que `LazyColumn`, solo renderiza los items visibles.
 */
@Preview(showBackground = true)
@Composable
fun LazyRowExampleScreen() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp), // Debe tener una altura definida
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre items
    ) {
        // Muestra 50 items
        items(50) { index ->
            Box(
                modifier = Modifier
                    .size(100.dp) // Items cuadrados
                    .background(if (index % 2 == 0) Color.Cyan else Color.Magenta),
                contentAlignment = Alignment.Center
            ) {
                Text("Item $index")
            }
        }
    }
}

// --- 10. LazyVerticalGrid ---
/**
 * `LazyVerticalGrid` es como `RecyclerView` con `GridLayoutManager`.
 * Muestra una cuadrícula de items que se puede scrollear verticalmente.
 * - `GridCells.Fixed(3)` define una cuadrícula de 3 columnas fijas.
 * - `GridCells.Adaptive(100.dp)` crea tantas columnas como quepan (mín 100.dp).
 */
@Preview(showBackground = true)
@Composable
fun LazyVerticalGridExampleScreen() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp), // Tantas columnas de 100.dp como quepan
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(100) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text("Grid $index")
            }
        }
    }
}

// --- 11. FlowRow ---
/**
 * `FlowRow` es como un `Row`, pero si los elementos no caben en una línea,
 * "fluyen" (wrap) a la línea siguiente.
 * Es perfecto para nubes de "tags" o "chips".
 * (Requiere @OptIn(ExperimentalLayoutApi::class))
 */
@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun FlowRowExampleScreen() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Espacio horizontal
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio vertical
    ) {
        val tags = listOf(
            "Kotlin", "Jetpack Compose", "Android", "UI", "Layouts",
            "K2", "Narwhal", "Programación", "Móvil", "Google",
            "FlowRow", "Ejemplo"
        )

        tags.forEach { tag ->
            Text(
                text = tag,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            )
        }
    }
}

// --- 12. BoxWithConstraints ---
/**
 * `BoxWithConstraints` es un `Box` que te da información sobre el
 * espacio que tiene disponible (sus `constraints`).
 * Es útil para crear diseños responsivos.
 * Te da `minWidth`, `maxWidth`, `minHeight`, `maxHeight`.
 */
@Preview(showBackground = true)
@Composable
fun BoxWithConstraintsExampleScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "Layout Responsivo",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
        ) {

            // 'this' es el scope de BoxWithConstraintsScope
            // Podemos consultar maxWidth
            if (maxWidth < 300.dp) {
                // Si es muy estrecho, muestra una Columna
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Pantalla Estrecha", color = Color.Red, fontSize = 20.sp)
                    DemoBox("A", Color.Cyan)
                }
            } else {
                // Si es ancho, muestra una Fila
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Pantalla Ancha", color = Color.Green, fontSize = 20.sp)
                    DemoBox("A", Color.Cyan)
                    DemoBox("B", Color.Yellow)
                }
            }
        }

        Text(
            "Intenta rotar el teléfono (en el emulador) o cambiar el tamaño de la ventana (si usas un Preview re-dimensionable) para ver el cambio.",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}