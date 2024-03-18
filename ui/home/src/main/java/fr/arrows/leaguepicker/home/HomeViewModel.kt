package fr.arrows.leaguepicker.home

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.arrows.leaguepicker.common.viewmodel.BaseViewModel
import fr.arrows.leaguepicker.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState as StateFlow<HomeState>

}
