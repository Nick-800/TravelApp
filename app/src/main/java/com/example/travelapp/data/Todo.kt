package com.example.travelapp.data

import com.example.travelapp.ui.theme.tasks.TaskStatus


data class Todo(
    val id: Int,
    var title: String,
    var description: String,
    var status: TaskStatus
)

