package fr.arrows.leaguepicker.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.arrows.leaguepicker.common.compose.search.SearchBarState
import fr.arrows.leaguepicker.common.model.leagues.League
import fr.arrows.leaguepicker.common.model.leagues.toUiModel
import fr.arrows.leaguepicker.common.model.snackbar.SnackbarType
import fr.arrows.leaguepicker.common.model.teams.toUiModel
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

    private val _searchItemsState = MutableStateFlow<List<League>>(listOf())
    val searchItemsState = _searchBarState.combine(_searchItemsState) { state, items ->
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
        initialValue = _searchItemsState.value
    )

    /* Events */

    fun launchEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.RefreshSearch -> onSearchTextChanged(text = event.text)
            HomeEvent.ToggleSearch -> onSearchToggled()
            HomeEvent.ClearSearchBar -> clearSearchBar()
            HomeEvent.FetchLeagues -> fetchLeagues()
            is HomeEvent.FetchTeamsFromLeagueId -> fetchTeamsFromLeagueId(event.id)
        }
    }

    /* Leagues */

    private fun fetchLeagues() = viewModelScope.launch {
        updateUi {
            isLoading = true
            leagues = null
            teams = null
        }

        val result = interactor.fetchLeagues()

        result.exceptionOrNull()?.let {
            showSnackBar(
                message = GENERIC_ERROR,
                type = SnackbarType.Error
            )
            updateUi { isLoading = false }
        } ?: run {
            result.getOrNull()?.leagues?.map { itemEntity ->
                itemEntity.toUiModel()
            }?.let { models ->
                _searchItemsState.value = models
                updateUi {
                    isLoading = false
                    leagues = models
                }
            }
        }
    }

    private fun fetchTeamsFromLeagueId(leagueId: String) =
        viewModelScope.launch {
            updateUi {
                isLoading = true
                leagues = null
                teams = null
            }

            val result = interactor.fetchTeams(leagueId)

            result.exceptionOrNull()?.let {
                showSnackBar(
                    message = GENERIC_ERROR,
                    type = SnackbarType.Error
                )
                updateUi { isLoading = false }
            } ?: run {
                result.getOrNull()?.teams?.map { itemEntity ->
                    itemEntity.toUiModel()
                }
                    ?.reversed()
                    ?.filterIndexed { i, _ -> i % 2 == 0 }
                    ?.let { models ->
                        updateUi {
                            isLoading = false
                            teams = models
                        }
                    }
            }
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

    private fun clearSearchBar() {
        _searchBarState.value = SearchBarState(
            isSearching = false,
            text = _searchBarState.value.text
        )
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
        const val GENERIC_ERROR = "An unknown error occurred. Please try again later"
    }

}
