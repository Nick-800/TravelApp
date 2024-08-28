package com.example.travelapp.ui.theme.tasks

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class TaskStatus(val icon: ImageVector) {
    NOT_STARTED(Icons.Filled.PlayArrow),
    IN_PROGRESS(Icons.Filled.DateRange),
    COMPLETED(Icons.Filled.CheckCircle)
}
