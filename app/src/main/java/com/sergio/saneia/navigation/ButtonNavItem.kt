package com.sergio.saneia.navigation

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class ButtonNavItem (
    val route: String,
    val icon: ImageVector?=null,
    val label: String,
    val painter: Painter?=null
)