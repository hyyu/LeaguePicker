package fr.arrows.leaguepicker.domain.teams.repository

import fr.arrows.leaguepicker.domain.teams.entity.FetchTeamsEntity

interface FetchTeamsRepository {
    suspend fun fetchTeams(leagueId: String): Result<FetchTeamsEntity>
}
