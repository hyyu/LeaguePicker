package fr.arrows.leaguepicker.domain.leagues.entity

interface FetchLeaguesEntity {
    val leagues: List<LeagueItemEntity>
}

interface LeagueItemEntity {
    val id: String
    val officialName: String
    val sport: String
    val alternateNames: String?
}
