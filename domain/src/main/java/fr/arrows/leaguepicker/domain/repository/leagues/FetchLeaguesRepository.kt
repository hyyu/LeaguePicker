package fr.arrows.leaguepicker.domain.repository.leagues

import fr.arrows.leaguepicker.domain.repository.entity.FetchLeagueEntity

interface FetchLeaguesRepository {
    suspend fun fetchLeagues(): Result<FetchLeagueEntity>
}
