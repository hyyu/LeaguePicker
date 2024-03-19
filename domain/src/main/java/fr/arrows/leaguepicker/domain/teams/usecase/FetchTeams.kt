package fr.arrows.leaguepicker.domain.teams.usecase

import fr.arrows.leaguepicker.domain.teams.entity.FetchTeamsEntity
import fr.arrows.leaguepicker.domain.teams.repository.FetchTeamsRepository
import javax.inject.Inject

class FetchTeams @Inject constructor(
    private val fetchTeamsRepository: FetchTeamsRepository
) {
    suspend operator fun invoke(): Result<FetchTeamsEntity> =
        fetchTeamsRepository.fetchTeams()

}
