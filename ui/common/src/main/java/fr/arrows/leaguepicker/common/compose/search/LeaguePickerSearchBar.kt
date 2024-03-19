package fr.arrows.leaguepicker.common.compose.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fr.arrows.leaguepicker.common.R
import fr.arrows.leaguepicker.common.model.leagues.League

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaguePickerSearchBar(
    searchBarState: SearchBarState,
    searchBarItems: List<League>,
    onSearchTextChanged: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    onSearchItemClicked: (String) -> Unit,
    onSearchBarIconClicked: () -> Unit
) {
    SearchBar(
        modifier = Modifier
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(25.dp)),
        query = searchBarState.text,
        onQueryChange = onSearchTextChanged,
        onSearch = onSearchTextChanged,
        active = searchBarState.isSearching,
        onActiveChange = onActiveChanged,
        trailingIcon = {
            if (searchBarState.isSearching) {
                IconButton(onClick = onSearchBarIconClicked) {
                    Icon(
                        painter = painterResource(R.drawable.ic_cancel),
                        contentDescription = "Filter",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    ) {
        SearchBarSuggestions(
            searchBarItems = searchBarItems,
            onItemClicked = onSearchItemClicked
        )
    }
}

@Composable
private fun SearchBarSuggestions(
    searchBarItems: List<League>,
    onItemClicked: (String) -> Unit
) {
    LazyColumn {
        items(searchBarItems) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(item.id) }
                    .padding(16.dp)
            ) {
                SearchResult(
                    modifier = Modifier,
                    text = item.name
                )
            }
        }
    }
}
