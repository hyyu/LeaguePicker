package fr.arrows.leaguepicker.common.compose.frame

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import fr.arrows.leaguepicker.common.compose.searchbar.SearchBarState
import fr.arrows.leaguepicker.common.model.leagues.League
import fr.arrows.leaguepicker.common.model.snackbar.LeaguepickerSnackbarData
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun Frame(
    modifier: Modifier = Modifier,
    snackbarState: StateFlow<LeaguepickerSnackbarData?>? = null,
    onShowSnackbar: () -> Unit,
    searchBarState: SearchBarState,
    searchBarItems: List<League>?,
    onSearchTextChanged: (String) -> Unit,
    onActiveChanged: (Boolean) -> Unit,
    onSearchBarIconClicked: () -> Unit,
    onSearchItemClicked: (String) -> Unit,
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
        onSearchBarIconClicked = onSearchBarIconClicked,
        onSearchItemClicked = onSearchItemClicked,
        content = content
    )

}
