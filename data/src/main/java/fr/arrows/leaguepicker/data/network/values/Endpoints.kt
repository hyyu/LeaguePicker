package fr.arrows.leaguepicker.data.network.values

import fr.arrows.leaguepicker.data.BuildConfig

object Endpoints {
    const val HOST = "www.thesportsdb.com/api/v1/json/${BuildConfig.SPORTS_API_KEY}"

    const val LEAGUES = "/all_leagues.php"
    const val TEAMS = "/search_all_teams.php"
}
