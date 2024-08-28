package com.example.travelapp.ui.theme.tasks

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.travelapp.data.Todo

class TodoViewModel : ViewModel() {
    val todoList = mutableStateListOf<Todo>()


}

