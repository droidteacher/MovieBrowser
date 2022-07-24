package hu.zsoltkiss.moviebrowser.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import hu.zsoltkiss.moviebrowser.R

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Colors.genericDivider
    @Composable
    get() = colorResource(R.color.genericDivider)


val Colors.popularityIconTint
    @Composable
    get() = colorResource(R.color.popularityIconTint)

val Colors.searchBarBackground
    @Composable
    get() = colorResource(R.color.searchBarBackground)