package fr.arrows.leaguepicker.common.model.snackbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import fr.arrows.leaguepicker.common.model.values.SnackbarValues

data class SnackbarView(
    val iconModifier: Modifier,
    val image: ImageVector
)

@Composable
fun SnackbarType.toModel() = when (this) {
    SnackbarType.Validation -> SnackbarView(
        iconModifier = Modifier.size(20.dp),
        image = Icons.Filled.CheckCircle
    )
    SnackbarType.Error -> SnackbarView(
        image = Icons.Filled.Info,
        iconModifier = Modifier
            .rotate(SnackbarValues.ERROR_ICON_ROTATION)
            .size(20.dp),
    )
    else -> SnackbarView(
        image = Icons.Filled.Info,
        iconModifier = Modifier
            .size(20.dp),
    )
}
