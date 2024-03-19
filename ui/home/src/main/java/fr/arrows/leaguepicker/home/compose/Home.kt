package fr.arrows.leaguepicker.home.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    val searchBarItems by viewModel.searchItemsState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.launchEvent(HomeEvent.FetchLeagues)
    }

    Frame(
        modifier = Modifier
            .fillMaxSize(),
        snackbarState = viewModel.snackbarState,
        onShowSnackbar = { viewModel.snackbarDisplayed() },
        searchBarState = searchBarState,
        searchBarItems = searchBarItems,
        onSearchTextChanged = { text ->
            viewModel.launchEvent(HomeEvent.RefreshSearch(text))
        },
        onActiveChanged = { viewModel.launchEvent(HomeEvent.ToggleSearch) },
        onSearchBarIconClicked = { viewModel.launchEvent(HomeEvent.ToggleSearch) },
        onSearchItemClicked = { itemId ->
            viewModel.launchEvent(HomeEvent.FetchTeamsFromLeagueId(itemId))
        },
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = uiState,
            onItemClicked = { itemId ->
                viewModel.launchEvent(HomeEvent.FetchTeamsFromLeagueId(itemId))
            }
        )
    }
}
