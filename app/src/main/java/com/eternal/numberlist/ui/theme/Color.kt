package com.eternal.numberlist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val lightPrimary = Color(0xFF04BDF5)
val lightOnPrimary = Color.White
val lightPrimaryContainer = Color(0xFF00BCD4) // SkyBlue 600
val lightOnPrimaryContainer = Color.Black

@Composable
fun ColorScheme.dividerColor(darkTheme: Boolean = isSystemInDarkTheme()) =
    if(darkTheme) Color(0xff333333) else Color.Gray
