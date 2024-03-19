package fr.arrows.leaguepicker.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.arrows.leaguepicker.common.compose.searchbar.SearchBarState
import fr.arrows.leaguepicker.common.model.leagues.League
import fr.arrows.leaguepicker.common.model.leagues.toUiModel
import fr.arrows.leaguepicker.common.model.snackbar.SnackbarType
import fr.arrows.leaguepicker.common.viewmodel.BaseViewModel
import fr.arrows.leaguepicker.home.event.HomeEvent
import fr.arrows.leaguepicker.home.interactor.HomeInteractor
import fr.arrows.leaguepicker.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val interactor: HomeInteractor
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    /* SearchBar states */
    private val _searchBarState = MutableStateFlow(SearchBarState())
    val searchBarState = _searchBarState.asStateFlow()

    private val _itemsState = MutableStateFlow<List<League>>(listOf())
    val itemsState = _searchBarState.combine(_itemsState) { state, items ->
        items.takeIf { state.text.isNotEmpty() }
            ?.filter { item ->
                item.name
                    .trim().uppercase()
                    .contains(state.text.trim().uppercase()) ||
                        item.alternateNames?.any {
                            it.trim().uppercase().contains(state.text.trim().uppercase())
                        } ?: false
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_FOR_STATEFLOW_COMBINE),
        initialValue = _itemsState.value
    )

    /* Events */

    fun launchEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.RefreshSearch -> onSearchTextChanged(text = event.text)
            HomeEvent.ToggleSearch -> onSearchToggled()
            HomeEvent.FetchLeagues -> fetchLeagues()
            is HomeEvent.FetchTeamsFromLeagueId -> fetchTeamsFromLeagueId(event.id)
        }
    }

    /* Leagues */

    private fun fetchLeagues() = viewModelScope.launch {
        updateUi { isLoading = true }

        val result = interactor.fetchLeagues()

        updateUi { isLoading = false }

        result.exceptionOrNull()?.let { cause ->
            Log.e("HomeViewModel/fetchLeagues", "ERROR:")
            Log.e("HomeViewModel/fetchLeagues", "${cause.message}")
            showSnackBar(
                message = cause.message ?: GENERIC_ERROR_WHEN_FETCHING_LEAGUES,
                type = SnackbarType.Error
            )
        } ?: run {
            result.getOrNull()?.leagues?.map { itemEntity ->
                itemEntity.toUiModel()
            }?.let { models -> _itemsState.value = models.toList() }
        }
    }

    private fun fetchTeamsFromLeagueId(leagueId: String) =
        viewModelScope.launch {

        }

    private fun onSearchToggled() =
        _searchBarState.value.isSearching.not()
            .let { newToggleValue ->
                if (!newToggleValue) {
                    onSearchTextChanged(
                        isSearching = false,
                        text = ""
                    )
                } else {
                    _searchBarState.value = SearchBarState(
                        isSearching = true,
                        text = _searchBarState.value.text
                    )
                }
            }

    private fun onSearchTextChanged(
        isSearching: Boolean = _searchBarState.value.isSearching,
        text: String
    ) {
        _searchBarState.value = SearchBarState(
            isSearching = isSearching,
            text = text
        )
    }

    /* Launch stateFlow collection */

    private fun updateUi(block: HomeState.Builder.() -> Unit) {
        _uiState.value = _uiState.value.build(block)
    }

    companion object {
        const val TIMEOUT_FOR_STATEFLOW_COMBINE = 5000L
        const val GENERIC_ERROR_WHEN_FETCHING_LEAGUES = "An unknown error occurred. Please try again later"
    }

}
