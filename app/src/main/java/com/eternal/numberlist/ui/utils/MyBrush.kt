package com.eternal.numberlist.ui.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val redBrush =
    Brush.linearGradient(
        listOf(Color(0xFFE57373), Color(0xFFEF5350), Color(0xFFF44336)),
        start = Offset.Zero, end = Offset.Infinite
    )

val blueBrush =
    Brush.linearGradient(
        listOf(Color(0xFF4FC3F7), Color(0xFF29B6F6), Color(0xFF03A9F4)),
        start = Offset.Zero, end = Offset.Infinite
    )

val greenBrush =
    Brush.linearGradient(
        listOf(Color(0xFF81C784), Color(0xFF66BB6A), Color(0xFF4CAF50)),
        start = Offset.Zero, end = Offset.Infinite
    )

val pinkBrush =
    Brush.linearGradient(
        listOf(Color(0xFFF06292), Color(0xFFEC407A), Color(0xFFE91E63)),
        start = Offset.Zero, end = Offset.Infinite
    )

val yellowBrush =
    Brush.linearGradient(
        listOf(Color(0xFFFFF176), Color(0xFFFFEE58), Color(0xFFFFEB3B)),
        start = Offset.Zero, end = Offset.Infinite
    )

val brushList = listOf(redBrush, greenBrush, blueBrush, pinkBrush, yellowBrush)
