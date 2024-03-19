package fr.arrows.leaguepicker.home.interactor

import fr.arrows.leaguepicker.domain.leagues.entity.FetchLeaguesEntity
import fr.arrows.leaguepicker.domain.leagues.usecase.FetchLeagues
import fr.arrows.leaguepicker.domain.teams.entity.FetchTeamsEntity
import fr.arrows.leaguepicker.domain.teams.usecase.FetchTeams
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val fetchLeagues: FetchLeagues,
    private val fetchTeams: FetchTeams
) {
    suspend fun fetchLeagues(): Result<FetchLeaguesEntity> = fetchLeagues.invoke()
    suspend fun fetchTeams(leagueId: String): Result<FetchTeamsEntity> = fetchTeams.invoke(leagueId)
}
