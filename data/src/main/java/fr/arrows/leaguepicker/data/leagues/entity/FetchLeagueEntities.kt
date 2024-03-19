package fr.arrows.leaguepicker.data.leagues.entity

import fr.arrows.leaguepicker.domain.leagues.entity.FetchLeaguesEntity
import fr.arrows.leaguepicker.domain.leagues.entity.LeagueItemEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchLeagueEntityImpl(
    @SerialName("leagues")
    override val leagues: List<LeagueItemEntityImpl>
) : FetchLeaguesEntity

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
