package com.example.jetpackcompose // Asegúrate que sea tu package name

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Ejemplos de Layouts")
    object ColumnExample : Screen("column", "Column")
    object RowExample : Screen("row", "Row")
    object BoxExample : Screen("box", "Box")
    object ScaffoldExample : Screen("scaffold", "Scaffold")
    object LazyRowExample : Screen("lazyrow", "LazyRow")
    object LazyVerticalGridExample : Screen("lazyverticalgrid", "LazyVerticalGrid")
    companion object {
        val examples = listOf(
            ColumnExample,
            RowExample,
            BoxExample,
            ScaffoldExample,
            LazyRowExample,
            LazyVerticalGridExample,
        )
    }
}