package com.example.todoapp.data

import com.example.todoapp.ui.theme.tasks.TaskStatus


data class Todo(
    val id: Int,
    var title: String,
    var description: String,
    var status: TaskStatus
)

