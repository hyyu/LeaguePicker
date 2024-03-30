package fr.arrows.leaguepicker.common.compose.snackbar

import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import fr.arrows.leaguepicker.common.model.snackbar.SnackbarType

@Composable
fun LeaguePickerSnackbar(data: SnackbarData) {
    LeaguePickerSnackbarContent(
        message = data.visuals.message,
        onClick = { data.dismiss() },
        type = data.visuals.actionLabel.snackBarLabelToType()
    )
}

private fun String?.snackBarLabelToType(): SnackbarType {
    return SnackbarType.mapLevelToSnackbarType(this)
}
