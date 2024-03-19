package fr.arrows.leaguepicker.domain.leagues.repository

import fr.arrows.leaguepicker.domain.leagues.entity.FetchLeaguesEntity

interface FetchLeaguesRepository {
    suspend fun fetchLeagues(): Result<FetchLeaguesEntity>
}
