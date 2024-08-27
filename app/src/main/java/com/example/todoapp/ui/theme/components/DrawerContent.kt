import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    drawerState: DrawerState,
    selectedItemIndex: Int,
    onSelectItem: (Int) -> Unit
) {
    val items = listOf("Home", "Profile", "Settings")
    val icons = listOf(Icons.Default.Home, Icons.Default.Person, Icons.Default.Settings)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.9f)
            .background(Color(0xFF64256F))
            .padding(16.dp)
    ) {
        Text(
            text = "My App",
            modifier = Modifier.padding(vertical = 24.dp),
            fontSize = 28.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(
                        if (selectedItemIndex == index) Color(0xFFD1C4E9) else Color.Transparent,
                        shape = MaterialTheme.shapes.medium
                    ),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color(0xFFD1C4E9)
                ),
                label = { Text(text = item, color = if (selectedItemIndex == index) Color.Black else Color.White) },
                selected = selectedItemIndex == index,
                onClick = {
                    onSelectItem(index)
                    scope.launch { drawerState.close() }
                },
                icon = {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = item,
                        tint = if (selectedItemIndex == index) Color.Black else Color.White
                    )
                }
            )
        }
    }
}
