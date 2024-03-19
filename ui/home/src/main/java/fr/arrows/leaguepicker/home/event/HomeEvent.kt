package fr.arrows.leaguepicker.home.event

sealed class HomeEvent {
    data class RefreshSearch(val text: String): HomeEvent()
    data object ClearSearchBar : HomeEvent()
    data object ToggleSearch : HomeEvent()
    data object FetchLeagues : HomeEvent()
    data class FetchTeamsFromLeagueId(val id: String): HomeEvent()
}
