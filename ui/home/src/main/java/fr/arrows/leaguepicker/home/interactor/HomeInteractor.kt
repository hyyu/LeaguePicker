package fr.arrows.leaguepicker.home.interactor

import fr.arrows.leaguepicker.domain.leagues.entity.FetchLeaguesEntity
import fr.arrows.leaguepicker.domain.leagues.usecase.FetchLeagues
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val fetchLeagues: FetchLeagues
) {
    suspend fun fetchLeagues(): Result<FetchLeaguesEntity> = fetchLeagues.invoke()
}
