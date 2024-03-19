package fr.arrows.leaguepicker.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.arrows.leaguepicker.common.compose.searchbar.SearchBarState
import fr.arrows.leaguepicker.common.model.searchbar.SearchBarItem
import fr.arrows.leaguepicker.common.model.snackbar.LeaguepickerSnackbarData
import fr.arrows.leaguepicker.common.model.snackbar.SnackbarType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

open class BaseViewModel : ViewModel() {

    /* Snackbar state */
    private val _snackbarState = MutableStateFlow<LeaguepickerSnackbarData?>(null)
    val snackbarState = _snackbarState as StateFlow<LeaguepickerSnackbarData?>

    /* SearchBar states */
    private val _searchBarState = MutableStateFlow(SearchBarState())
    val searchBarState = _searchBarState.asStateFlow()

    private val _itemsState = MutableStateFlow<List<SearchBarItem>>(listOf())
    val itemsState = _searchBarState.combine(_itemsState) { state, items ->
        items.takeIf { state.text.isNotEmpty() }
            ?.filter { item ->
                item.text.trim().uppercase().contains(state.text.trim().uppercase())
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_FOR_STATEFLOW_COMBINE),
        initialValue = _itemsState.value
    )

    protected fun showSnackBar(message: String, type: SnackbarType = SnackbarType.Error) {
        _snackbarState.value = LeaguepickerSnackbarData(message, type)
    }

    fun snackbarDisplayed() {
        _snackbarState.value = null
    }

    protected fun onSearchTextChanged(
        isSearching: Boolean = _searchBarState.value.isSearching,
        text: String
    ) {
        _searchBarState.value = SearchBarState(
            isSearching = isSearching,
            text = text
        )
    }

    protected fun onSearchToggled() =
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

    companion object {
        const val TIMEOUT_FOR_STATEFLOW_COMBINE = 5000L
    }

}
