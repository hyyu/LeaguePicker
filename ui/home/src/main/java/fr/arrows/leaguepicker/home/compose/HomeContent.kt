package fr.arrows.leaguepicker.home.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.arrows.leaguepicker.home.state.HomeState

@Composable
internal fun HomeContent(
    modifier: Modifier,
    state: HomeState
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
    }
}
