package com.roys.wolvnote.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val StandardColorScheme = lightColorScheme(
    primary = Green35,
    secondary = White,
    error = Red40,
    outline = Green40,
    tertiary = Transparent,
    primaryContainer = White,
    outlineVariant = Grey60
)

@Composable
fun WolvNoteTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = StandardColorScheme
    

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}