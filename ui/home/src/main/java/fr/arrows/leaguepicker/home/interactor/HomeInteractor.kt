package fr.arrows.leaguepicker.home.interactor

import fr.arrows.leaguepicker.domain.repository.entity.FetchLeagueEntity
import fr.arrows.leaguepicker.domain.usecase.leagues.FetchLeagues
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val fetchLeagues: FetchLeagues
) {
    suspend fun fetchLeagues(): Result<FetchLeagueEntity> = fetchLeagues.invoke()
}
