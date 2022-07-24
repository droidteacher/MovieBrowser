package hu.zsoltkiss.moviebrowser.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import hu.zsoltkiss.moviebrowser.R
import hu.zsoltkiss.moviebrowser.ui.theme.appBarHeader
import hu.zsoltkiss.moviebrowser.ui.theme.genericDivider

@Suppress("unused")
@Composable
fun MBAppBar(
    title: String,
    backNavigation: @Composable()(() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    style = appBarHeader,
                )
            }
        },
        modifier = Modifier.testTag("MBAppBar"),
        backgroundColor = MaterialTheme.colors.background,
        navigationIcon = backNavigation
    )
    Divider(
        color = MaterialTheme.colors.genericDivider,
        thickness = dimensionResource(R.dimen.divider_thickness)
    )
}