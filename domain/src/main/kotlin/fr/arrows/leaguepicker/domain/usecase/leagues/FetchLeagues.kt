package fr.arrows.leaguepicker.domain.usecase.leagues

import fr.arrows.leaguepicker.domain.repository.entity.FetchLeagueEntity
import fr.arrows.leaguepicker.domain.repository.leagues.FetchLeaguesRepository
import javax.inject.Inject

class FetchLeagues @Inject constructor(
    private val fetchLeaguesRepository: FetchLeaguesRepository
) {
    suspend operator fun invoke(): Result<FetchLeagueEntity> =
        fetchLeaguesRepository.fetchLeagues()

}
