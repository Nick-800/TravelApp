package com.example.travelapp.componets

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = Color.Red,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        modifier = Modifier.clickable { onClick }
    )
}