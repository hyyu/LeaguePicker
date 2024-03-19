package fr.arrows.leaguepicker.home.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import fr.arrows.leaguepicker.common.compose.frame.Frame
import fr.arrows.leaguepicker.home.HomeViewModel
import fr.arrows.leaguepicker.home.event.HomeEvent

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val searchBarState by viewModel.searchBarState.collectAsState()
    val searchItems by viewModel.itemsState.collectAsState()

    Frame(
        modifier = Modifier
            .fillMaxSize(),
        snackbarState = viewModel.snackbarState,
        onShowSnackbar = { viewModel.snackbarDisplayed() },
        searchBarState = searchBarState,
        searchBarItems = searchItems,
        onSearchTextChanged = { viewModel.launchEvent(HomeEvent.RefreshSearch(searchBarState.text)) },
        onActiveChanged = { viewModel.launchEvent(HomeEvent.ToggleSearch) }
    ) { paddingValues ->
    }

}
