package fr.arrows.leaguepicker.domain.teams.entity

interface FetchTeamsEntity {
    val leagues: List<TeamItemEntity>
}

interface TeamItemEntity {
    val badgeUrl: String
    val name: String
}
