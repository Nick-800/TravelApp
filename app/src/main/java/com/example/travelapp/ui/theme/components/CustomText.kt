package com.example.travelapp.ui.theme.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.travelapp.R



val customFont = FontFamily(
    Font(R.font.anek, FontWeight.Normal)
)

@Composable
fun CustomText(
    text: String,
    fontSize: Int = 16,
    fontFamily: FontFamily = customFont,
    fontWeight: FontWeight = FontWeight.Normal,
    fontStyle: FontStyle = FontStyle.Normal,
    color: Color = Color.Black,
    @SuppressLint("ModifierParameter") modifier: Modifier? = Modifier
) {

        Text(
            text = text,
            modifier = modifier ?: Modifier,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontFamily = fontFamily,
                fontWeight = fontWeight,
                fontStyle = fontStyle,
                color = color
            )
        )
    }

