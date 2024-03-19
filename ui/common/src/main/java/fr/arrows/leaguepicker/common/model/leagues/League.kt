package fr.arrows.leaguepicker.common.model.leagues

import fr.arrows.leaguepicker.domain.repository.entity.LeagueItemEntity

data class League(
    val id: String,
    val name: String,
    val sport: String,
    val alternateNames: Sequence<String>? = null
)

fun LeagueItemEntity.toUiModel() = League(
    id = id,
    name = officialName,
    sport = sport,
    alternateNames = alternateNames?.splitToSequence(", ")
)
