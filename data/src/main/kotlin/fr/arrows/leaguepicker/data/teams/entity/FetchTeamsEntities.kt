package fr.arrows.leaguepicker.data.teams.entity

import fr.arrows.leaguepicker.domain.teams.entity.FetchTeamsEntity
import fr.arrows.leaguepicker.domain.teams.entity.TeamItemEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchTeamsEntityImpl(
    @SerialName("teams")
    override val teams: List<TeamItemEntityImpl>
) : FetchTeamsEntity

@Serializable
data class TeamItemEntityImpl(

    @SerialName("strTeam")
    override val name: String,

    @SerialName("strTeamBadge")
    override val badgeUrl: String,

) : TeamItemEntity
