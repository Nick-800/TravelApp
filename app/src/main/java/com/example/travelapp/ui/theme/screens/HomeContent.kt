package com.example.travelapp.ui.theme.screens

import com.example.travelapp.ui.theme.tasks.TodoViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelapp.data.Todo
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelapp.ui.theme.tasks.TaskStatus

@Composable
fun HomeContent(nav: NavController) {
    val viewModel: TodoViewModel = viewModel()
    val showAddDialog = remember { mutableStateOf(false) }
    val showEditDialog = remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<Todo?>(null) }
    var taskToEdit by remember { mutableStateOf<Todo?>(null) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(viewModel.todoList) { item ->
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { selectedTask = item },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF9C27B0).copy(alpha = 0.2f)
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = item.status.icon,
                            contentDescription = item.status.name,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(16.dp),
                        )
                    }
                    IconButton(
                        modifier = Modifier.padding(16.dp),
                        onClick = { viewModel.todoList.remove(item) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            shape = CircleShape,
            onClick = { showAddDialog.value = true },
            containerColor = Color(0xFF9C27B0),
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Task",
                tint = Color.White
            )
        }
    }

    if (showAddDialog.value) {
        MyAlertDialog(
            showErrorDialog = showAddDialog,
            todoList = viewModel.todoList
        )
    }

    if (showEditDialog.value) {
        taskToEdit?.let { task ->
            MyAlertDialog(
                showErrorDialog = showEditDialog,
                todoList = viewModel.todoList,
                initialTask = task
            )
        }
    }

    selectedTask?.let {
        TaskDetailsDialog(
            task = it,
            onDismiss = { selectedTask = null },
            onEdit = { task ->
                taskToEdit = task
                showEditDialog.value = true
            }
        )
    }
}


@Composable
fun MyAlertDialog(
    showErrorDialog: MutableState<Boolean>,
    todoList: SnapshotStateList<Todo>,
    initialTask: Todo? = null
) {
    var newToDo by remember { mutableStateOf(initialTask?.title ?: "") }
    var newDescription by remember { mutableStateOf(initialTask?.description ?: "") }
    var selectedStatus by remember { mutableStateOf(initialTask?.status ?: TaskStatus.NOT_STARTED) }

    AlertDialog(
        onDismissRequest = { showErrorDialog.value = false },
        title = { Text(text = if (initialTask == null) "Add New Task" else "Edit Task") },
        text = {
            Column {
                OutlinedTextField(
                    value = newToDo,
                    onValueChange = { newToDo = it },
                    label = { Text("Task Title") },
                    keyboardOptions = KeyboardOptions.Default
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newDescription,
                    onValueChange = { newDescription = it },
                    label = { Text("Task Description") },
                    keyboardOptions = KeyboardOptions.Default
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Status:")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TaskStatus.values().forEach { status ->
                        IconButton(
                            onClick = { selectedStatus = status },
                            modifier = Modifier.background(
                                color = if (selectedStatus == status) {
                                    Color(0xFF9C27B0).copy(alpha = 0.3f)
                                }
                                else Color.Transparent,
                                shape = CircleShape
                            )
                        ) {
                            Icon(
                                imageVector = status.icon,
                                contentDescription = status.name
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (newToDo.isNotEmpty()) {
                        if (initialTask == null) {
                            todoList.add(
                                Todo(
                                    id = System.currentTimeMillis().toInt(),
                                    title = newToDo,
                                    description = newDescription,
                                    status = selectedStatus
                                )
                            )
                        } else {
                            initialTask.title = newToDo
                            initialTask.description = newDescription
                            initialTask.status = selectedStatus
                        }
                        showErrorDialog.value = false
                    }
                }
            ) {
                Text(if (initialTask == null) "Add Task" else "Update Task")
            }
        },
        dismissButton = {
            TextButton(onClick = { showErrorDialog.value = false }) {
                Text("Cancel")
            }
        }
    )
}


@Composable
fun TaskDetailsDialog(task: Todo, onDismiss: () -> Unit, onEdit: (Todo) -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = task.title) },
        text = {
            Column {
                Text(text = task.description)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Status: ${task.status}")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Close")
            }
        },
        dismissButton = {
            TextButton(onClick = { onEdit(task) }) {
                Text("Edit")
            }
        }
    )
}


