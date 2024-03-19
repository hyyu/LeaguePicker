package fr.arrows.leaguepicker.domain.repository.entity

interface FetchLeagueEntity {
    val leagues: List<LeagueItemEntity>
}

interface LeagueItemEntity {
    val id: String
    val officialName: String
    val sport: String
    val alternateNames: String?
}
