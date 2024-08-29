package com.example.travelapp.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.travelapp.R


data class Category(
    val name: String,
    val imageUri: Int? = R.drawable.error
)

@Composable
fun AdminScreen() {
    var categories by remember { mutableStateOf(listOf(Category("Mountain", R.drawable.error), Category("Lake", R.drawable.error), Category("Desert", R.drawable.error), Category("Forest", R.drawable.error))) }
    var travelcards by remember {
        mutableStateOf(
            listOf(
                Travelcard(imageUri = 0, "NA", "NA loc", 4.5, "Mountain"),
                Travelcard(0, "NA", "NA loc", 4.5, "Lake")
            )
        )
    }

    var showDialog by remember { mutableStateOf(false) }
    var isEditingCategory by remember { mutableStateOf(false) }
    var isEditingTravelcard by remember { mutableStateOf(false) }
    var currentCategory by remember { mutableStateOf<Category?>(null) }
    var currentTravelcard by remember { mutableStateOf<Travelcard?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                currentCategory = null
                currentTravelcard = null
                isEditingCategory = false
                isEditingTravelcard = false
                showDialog = true
            }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text("Categories", style = MaterialTheme.typography.headlineSmall)
            LazyColumn {
                items(categories) { category ->
                    CategoryItem(
                        category = category,
                        onEdit = {
                            currentCategory = category
                            isEditingCategory = true
                            showDialog = true
                        },
                        onDelete = {
                            categories = categories - category
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Travelcards", style = MaterialTheme.typography.headlineSmall)
            LazyColumn {
                items(travelcards) { travelcard ->
                    TravelcardItem(
                        travelcard = travelcard,
                        onEdit = {
                            currentTravelcard = travelcard
                            isEditingTravelcard = true
                            showDialog = true
                        },
                        onDelete = {
                            travelcards = travelcards - travelcard
                        }
                    )
                }
            }
        }
    }

    if (showDialog) {
        EditOrAddDialog(
            isEditingCategory = isEditingCategory,
            isEditingTravelcard = isEditingTravelcard,
            currentCategory = currentCategory,
            currentTravelcard = currentTravelcard,
            onCategorySubmit = { name, imageUri ->
                if (isEditingCategory) {
                    categories = categories.map {
                        if (it == currentCategory) it.copy(name = name, imageUri = imageUri) else it
                    }
                } else {
                    categories = categories + Category(name, imageUri)
                }
                showDialog = false
            },
            onTravelcardSubmit = { name, location, rating, imageUri ->
                if (isEditingTravelcard) {
                    travelcards = travelcards.map {
                        if (it == currentTravelcard) it.copy(title = name, location = location, rating = rating.toDouble(), imageUri = imageUri) else it
                    }
                } else {
                    travelcards = travelcards + Travelcard(imageUri, name, location, rating.toDouble(), name)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun CategoryItem(category: Category, onEdit: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(category.name)
        Row {
            IconButton(onClick = onEdit) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun TravelcardItem(travelcard: Travelcard, onEdit: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(travelcard.title)
        Row {
            IconButton(onClick = onEdit) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun EditOrAddDialog(
    isEditingCategory: Boolean,
    isEditingTravelcard: Boolean,
    currentCategory: Category?,
    currentTravelcard: Travelcard?,
    onCategorySubmit: (name: String, imageUri: Int?) -> Unit,
    onTravelcardSubmit: (name: String, location: String, rating: String, imageUri: Int) -> Unit
) {
    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                if (isEditingCategory || isEditingTravelcard) {
                    EditForm(
                        isEditingCategory = isEditingCategory,
                        currentCategory = currentCategory,
                        currentTravelcard = currentTravelcard,
                        onSubmit = { name, location, rating, imageUri ->
                            if (isEditingCategory) {
                                onCategorySubmit(name, imageUri)
                            } else {
                                onTravelcardSubmit(name, location, rating, imageUri ?: R.drawable.error)
                            }
                        }
                    )
                } else {
                    AddForm(onSubmit = { name, location, rating, imageUri ->
                        if (location.isNotEmpty()) {
                            onTravelcardSubmit(name, location, rating, imageUri)
                        } else {
                            onCategorySubmit(name, imageUri)
                        }
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelApp() {
    val navController = rememberNavController()
    ModalNavigationDrawer(
        drawerContent = { Drawer(navController) }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Travel App") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = { BottomBar(navController) },
        ) { innerPadding ->
            NavHost(navController, startDestination = "home", Modifier.padding(innerPadding)) {
                composable("home") { AdminScreen() }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar(
        contentPadding = PaddingValues(horizontal = 16.dp),
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        val iconTint = Color.White
        val iconSize = 24.dp

        IconButton(onClick = { navController.navigate("home") }) {
            Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = "Home", tint = iconTint, modifier = Modifier.size(iconSize))
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ic_location), contentDescription = "Location", tint = iconTint, modifier = Modifier.size(iconSize))
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ic_explore), contentDescription = "Explore", tint = iconTint, modifier = Modifier.size(iconSize))
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { }) {
            Icon(painter = painterResource(id = R.drawable.ic_calendar), contentDescription = "Calendar", tint = iconTint, modifier = Modifier.size(iconSize))
        }
    }
}

@Composable
fun Drawer(navController: NavHostController) {
    Column(modifier = Modifier.background(color = Color.Gray)) {
        Text("Item 1", modifier = Modifier.clickable { navController.navigate("home") })
        Text("Item 2", modifier = Modifier.clickable { navController.navigate("home") })
    }
}


@Composable
fun EditForm(
    isEditingCategory: Boolean,
    currentCategory: Category?,
    currentTravelcard: Travelcard?,
    onSubmit: (name: String, location: String, rating: String, imageUri: Int?) -> Unit
) {
    var name by remember { mutableStateOf(currentCategory?.name ?: currentTravelcard?.title ?: "") }
    var location by remember { mutableStateOf(currentTravelcard?.location ?: "") }
    var rating by remember { mutableStateOf(currentTravelcard?.rating?.toString() ?: "") }
    var imageUri by remember { mutableStateOf(currentCategory?.imageUri ?: R.drawable.error) }

    Column {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        if (!isEditingCategory) {
            TextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = rating,
                onValueChange = { rating = it },
                label = { Text("Rating") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = { onSubmit(name, location, rating, imageUri) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}

@Composable
fun AddForm(
    onSubmit: (name: String, location: String, rating: String, imageUri: Int) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf(R.drawable.error) }

    Column {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rating") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { onSubmit(name, location, rating, imageUri) },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add")
        }
    }
}

