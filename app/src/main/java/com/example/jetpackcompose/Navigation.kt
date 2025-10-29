package com.example.tuproyecto // Asegúrate que sea tu package name

/**
 * Define todas las pantallas (rutas) en la aplicación
 * para una navegación segura.
 */
sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Ejemplos de Layouts")
    object ColumnExample : Screen("column", "Column")
    object RowExample : Screen("row", "Row")
    object BoxExample : Screen("box", "Box")
    object SpacerExample : Screen("spacer", "Spacer")
    object SurfaceExample : Screen("surface", "Surface")
    object ScaffoldExample : Screen("scaffold", "Scaffold")
    object ConstraintLayoutExample : Screen("constraintlayout", "ConstraintLayout")
    object LazyColumnExample : Screen("lazycolumn", "LazyColumn")
    object LazyRowExample : Screen("lazyrow", "LazyRow")
    object LazyVerticalGridExample : Screen("lazyverticalgrid", "LazyVerticalGrid")
    object FlowRowExample : Screen("flowrow", "FlowRow")
    object BoxWithConstraintsExample : Screen("boxwithconstraints", "BoxWithConstraints")

    // Lista de todos los ejemplos para mostrar en la pantalla principal
    companion object {
        val examples = listOf(
            ColumnExample,
            RowExample,
            BoxExample,
            SpacerExample,
            SurfaceExample,
            ScaffoldExample,
            ConstraintLayoutExample,
            LazyColumnExample,
            LazyRowExample,
            LazyVerticalGridExample,
            FlowRowExample,
            BoxWithConstraintsExample
        )
    }
}