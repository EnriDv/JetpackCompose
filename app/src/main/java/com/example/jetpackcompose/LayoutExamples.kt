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

        Text(
            text = "¡Esquina!",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(Color.Yellow)
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScaffoldExampleScreen() {
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


@Preview(showBackground = true)
@Composable
fun LazyColumnExampleScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text("Cabecera de la Lista", style = MaterialTheme.typography.titleLarge)
        }
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

@Preview(showBackground = true)
@Composable
fun LazyRowExampleScreen() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Muestra 50 items
        items(50) { index ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(if (index % 2 == 0) Color.Cyan else Color.Magenta),
                contentAlignment = Alignment.Center
            ) {
                Text("Item $index")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LazyVerticalGridExampleScreen() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
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