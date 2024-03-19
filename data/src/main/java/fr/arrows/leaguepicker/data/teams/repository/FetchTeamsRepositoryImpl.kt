package fr.arrows.leaguepicker.data.teams.repository

import fr.arrows.leaguepicker.data.network.values.Endpoints
import fr.arrows.leaguepicker.data.teams.entity.FetchTeamsEntityImpl
import fr.arrows.leaguepicker.domain.teams.entity.FetchTeamsEntity
import fr.arrows.leaguepicker.domain.teams.repository.FetchTeamsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchTeamsRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : FetchTeamsRepository {
    override suspend fun fetchTeams(leagueId: String): Result<FetchTeamsEntity> = runCatching {
        withContext(Dispatchers.IO) {
            httpClient.get(Endpoints.TEAMS) {
                url {
                    parameters.append("id", leagueId)
                }
            }
        }.body<FetchTeamsEntityImpl>()
    }

}
