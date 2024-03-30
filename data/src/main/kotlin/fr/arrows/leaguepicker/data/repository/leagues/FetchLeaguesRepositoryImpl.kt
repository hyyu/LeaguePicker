package fr.arrows.leaguepicker.data.repository.leagues

import fr.arrows.leaguepicker.data.network.values.Endpoints
import fr.arrows.leaguepicker.data.repository.entity.FetchLeagueEntityImpl
import fr.arrows.leaguepicker.domain.repository.entity.FetchLeagueEntity
import fr.arrows.leaguepicker.domain.repository.leagues.FetchLeaguesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchLeaguesRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : FetchLeaguesRepository {
    override suspend fun fetchLeagues(): Result<FetchLeagueEntity> = runCatching {
        withContext(Dispatchers.IO) {
            httpClient.get(Endpoints.LEAGUES)
        }.body<FetchLeagueEntityImpl>()
    }

}
