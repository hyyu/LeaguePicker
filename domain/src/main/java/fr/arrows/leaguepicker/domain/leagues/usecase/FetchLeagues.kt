package fr.arrows.leaguepicker.domain.leagues.usecase

import fr.arrows.leaguepicker.domain.leagues.entity.FetchLeaguesEntity
import fr.arrows.leaguepicker.domain.leagues.repository.FetchLeaguesRepository
import javax.inject.Inject

class FetchLeagues @Inject constructor(
    private val fetchLeaguesRepository: FetchLeaguesRepository
) {
    suspend operator fun invoke(): Result<FetchLeaguesEntity> =
        fetchLeaguesRepository.fetchLeagues()

}
