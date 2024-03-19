package fr.arrows.leaguepicker.home.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.arrows.leaguepicker.common.model.leagues.League
import fr.arrows.leaguepicker.home.compose.values.HomeValues
import fr.arrows.leaguepicker.home.state.HomeState

@Composable
internal fun HomeContent(
    modifier: Modifier,
    state: HomeState,
    onItemClicked: (String) -> Unit
) {
    Column(
      modifier = modifier
    ) {
        if (state.isLoading) {
            Loader(modifier = Modifier.fillMaxSize())
        } else {
            state.leagues?.let {
                LeaguesColumn(
                    leagues = it,
                    onItemClicked = onItemClicked
                )
            }
            state.teams?.let {
                TeamsGrid(
                    teams = it
                )
            }
        }
    }
}

@Composable
private fun Loader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun LeaguesColumn(
    modifier: Modifier = Modifier,
    leagues: List<League>,
    onItemClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(leagues) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(item.id) }
                    .padding(16.dp)
            ) {
                Text(
                    modifier = modifier,
                    text = item.name
                )
            }
        }
    }
}

@Composable
private fun TeamsGrid(
    modifier: Modifier = Modifier,
    teams: List<League>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(HomeValues.NUMBER_OF_TEAM_COLUMNS)
    ) {
        items(teams) {team ->
            // TODO Change model type when teams will be implemented
            // TODO Composable for a team item cell
        }
    }
}
