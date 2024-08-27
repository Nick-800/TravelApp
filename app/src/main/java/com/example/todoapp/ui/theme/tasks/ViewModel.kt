package com.example.todoapp.ui.theme.tasks

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.Todo

class TodoViewModel : ViewModel() {
    val todoList = mutableStateListOf<Todo>()


}

