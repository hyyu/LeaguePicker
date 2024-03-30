package fr.arrows.leaguepicker.home.state

import fr.arrows.leaguepicker.common.model.leagues.League
import fr.arrows.leaguepicker.common.model.teams.Team

data class HomeState(
    val isLoading: Boolean = false,
    val leagues: List<League>? = null,
    val teams: List<Team>? = null
) {
    fun build(block: Builder.() -> Unit) = Builder(HomeState()).apply(block).build()

    class Builder(uiModel: HomeState) {
        var isLoading = uiModel.isLoading
        var leagues = uiModel.leagues
        var teams = uiModel.teams

        fun build(): HomeState =
            HomeState(
                isLoading = isLoading,
                leagues = leagues,
                teams = teams
            )
    }
}
