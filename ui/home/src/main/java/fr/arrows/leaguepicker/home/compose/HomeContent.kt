package fr.arrows.leaguepicker.home.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    state: HomeState
    state: HomeState,
    onItemClicked: (String) -> Unit
) {
    if (state.isLoading) {
        Loader(modifier = modifier)
    } else {
        state.leagues?.let {
            LeaguesColumn(
                modifier = modifier,
                leagues = it,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Composable
private fun Loader(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        state.takeIf { it.isLoading }
            ?.let {
                CircularProgressIndicator()
            }
        CircularProgressIndicator()
    }
}

@Composable
private fun LeaguesColumn(
    modifier: Modifier,
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

    }
}
