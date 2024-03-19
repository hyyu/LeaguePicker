package fr.arrows.leaguepicker.home

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.arrows.leaguepicker.common.viewmodel.BaseViewModel
import fr.arrows.leaguepicker.home.event.HomeEvent
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    fun launchEvent(event: HomeEvent) = when (event) {
        is HomeEvent.RefreshSearch -> onSearchTextChanged(text = event.text)
        HomeEvent.ToggleSearch -> onSearchToggled()
        HomeEvent.FetchLeagues -> fetchLeagues()
    }

    private fun fetchLeagues() {

    }

}
