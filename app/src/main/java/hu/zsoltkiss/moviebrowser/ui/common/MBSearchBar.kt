package hu.zsoltkiss.moviebrowser.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.zsoltkiss.moviebrowser.ui.theme.searchBarBackground


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MBSearchBar(
    searchExpression: String,
    onUserInputChange: (String) -> Unit,
    onSubmitSearch: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
        ,
        color = MaterialTheme.colors.primary,
        elevation = 8.dp,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            val keyboardController = LocalSoftwareKeyboardController.current

            TextField(
                value = searchExpression,
                onValueChange = {
                    onUserInputChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(MaterialTheme.colors.searchBarBackground),
                textStyle = TextStyle(
                    color = MaterialTheme.colors.onSurface
                ),
                label = {
                    Text(text = "Search")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onSubmitSearch(searchExpression)
                        keyboardController?.hide()
                    }
                ),
                leadingIcon = {
                    Icon(Icons.Filled.Search, contentDescription = "Search icon")
                },
                trailingIcon = {
                    IconButton(onClick = { onUserInputChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear icon")
                    }

                }
            )
        }
    }
}