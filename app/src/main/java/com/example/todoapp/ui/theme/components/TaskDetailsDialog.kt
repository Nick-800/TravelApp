import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import com.example.todoapp.data.Todo

@Composable
fun TaskDetailsDialog(task: Todo, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = task.title, textAlign = TextAlign.Center) },
        text = { Text(text = task.description) },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Close")
            }
        }
    )
}
