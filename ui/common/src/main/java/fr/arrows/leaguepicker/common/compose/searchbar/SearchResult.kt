package fr.arrows.leaguepicker.common.compose.searchbar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchResult(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text
    )
}
