package fr.arrows.leaguepicker.data.repository.entity

import fr.arrows.leaguepicker.domain.repository.entity.FetchLeagueEntity
import fr.arrows.leaguepicker.domain.repository.entity.LeagueItemEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchLeagueEntityImpl(
    @SerialName("leagues")
    override val leagues: List<LeagueItemEntityImpl>
) : FetchLeagueEntity

@Serializable
data class LeagueItemEntityImpl(
    @SerialName("idLeague")
    override val id: String,

    @SerialName("strLeague")
    override val officialName: String,

    @SerialName("strSport")
    override val sport: String,

    @SerialName("strLeagueAlternate")
    override val alternateNames: String? = null
) : LeagueItemEntity
