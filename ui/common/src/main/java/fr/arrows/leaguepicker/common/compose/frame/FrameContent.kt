package fr.arrows.leaguepicker.common.compose.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.arrows.leaguepicker.common.compose.searchbar.LeaguePickerSearchBar
import fr.arrows.leaguepicker.common.compose.searchbar.SearchBarState
import fr.arrows.leaguepicker.common.compose.snackbar.LeaguePickerSnackbar
import fr.arrows.leaguepicker.common.model.leagues.League

@Composable
internal fun FrameContent(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    searchBarState: SearchBarState,
    searchBarItems: List<League>,
    onSearchTextChanged: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    onSearchBarIconClicked: () -> Unit,
    onSearchItemClicked: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LeaguePickerSearchBar(
                    searchBarState = searchBarState,
                    searchBarItems = searchBarItems,
                    onSearchTextChanged = onSearchTextChanged,
                    onActiveChanged = onActiveChanged,
                    onSearchItemClicked = onSearchItemClicked,
                    onSearchBarIconClicked = onSearchBarIconClicked,
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                LeaguePickerSnackbar(
                    data = data
                )
            }
        },
        content = content
    )

}
