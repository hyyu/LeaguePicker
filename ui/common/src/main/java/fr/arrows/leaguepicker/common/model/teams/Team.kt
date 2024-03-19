package fr.arrows.leaguepicker.common.model.teams

import fr.arrows.leaguepicker.domain.teams.entity.TeamItemEntity

data class Team(
    val name: String,
    val badgeUrl: String,
)

fun TeamItemEntity.toUiModel() = Team(
    name = name,
    badgeUrl = badgeUrl,
)
