package fr.arrows.leaguepicker.common.compose.frame

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.arrows.leaguepicker.common.compose.searchbar.SearchBarState
import fr.arrows.leaguepicker.common.compose.searchbar.SearchResult
import fr.arrows.leaguepicker.common.compose.snackbar.LeaguePickerSnackbar
import fr.arrows.leaguepicker.common.model.searchbar.SearchBarItem
import fr.arrows.leaguepicker.common.model.snackbar.LeaguepickerSnackbarData
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun Frame(
    modifier: Modifier = Modifier,
    snackbarState: StateFlow<LeaguepickerSnackbarData?>? = null,
    onShowSnackbar: () -> Unit,
    searchBarState: SearchBarState,
    searchBarItems: List<SearchBarItem>?,
    onSearchTextChanged: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val showSnackbar: (LeaguepickerSnackbarData) -> Unit = {
        coroutineScope.launch {
            onShowSnackbar()
            snackbarHostState.showSnackbar(
                message = it.message,
                actionLabel = it.type.level.name,
                duration = SnackbarDuration.Short
            )
        }
    }

    snackbarState?.collectAsState()?.value.also { data ->
        data?.let {
            showSnackbar(it)
        }
    }

    FrameContent(
        modifier = modifier,
        snackbarHostState = snackbarHostState,
        searchBarState = searchBarState,
        searchBarItems = searchBarItems ?: listOf(),
        onSearchTextChanged = onSearchTextChanged,
        onActiveChanged = onActiveChanged,
        content = content
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FrameContent(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    searchBarState: SearchBarState,
    searchBarItems: List<SearchBarItem>,
    onSearchTextChanged: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                query = searchBarState.text,
                onQueryChange = onSearchTextChanged,
                onSearch = onSearchTextChanged,
                active = searchBarState.isSearching,
                onActiveChange = onActiveChanged
            ) {
                LazyColumn {
                    items(searchBarItems) { item ->
                        SearchResult(text = item.text)
                    }
                }
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
