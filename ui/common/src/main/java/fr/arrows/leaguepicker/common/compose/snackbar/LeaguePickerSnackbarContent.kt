package fr.arrows.leaguepicker.common.compose.snackbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fr.arrows.leaguepicker.common.R.string
import fr.arrows.leaguepicker.common.model.snackbar.SnackbarType
import fr.arrows.leaguepicker.common.model.snackbar.SnackbarView
import fr.arrows.leaguepicker.common.model.snackbar.toModel

@Composable
fun LeaguePickerSnackbarContent(
    message: String?,
    onClick: () -> Unit,
    type: SnackbarType = SnackbarType.Info
) {
    val model = type.toModel()

    val containerColor = when (type) {
        SnackbarType.Validation -> MaterialTheme.colorScheme.tertiaryContainer
        SnackbarType.Error -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.surface
    }

    val onContainerColor = when (type) {
        SnackbarType.Validation -> MaterialTheme.colorScheme.onTertiaryContainer
        SnackbarType.Error -> MaterialTheme.colorScheme.onErrorContainer
        else -> MaterialTheme.colorScheme.onSurface
    }

    val iconTint = if (isSystemInDarkTheme())
        onContainerColor
    else
        when (type) {
            SnackbarType.Validation -> MaterialTheme.colorScheme.tertiary
            SnackbarType.Error -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.onSurface
        }

    SnackbarShape(
        model = model,
        containerColor = containerColor,
        onContainerColor = onContainerColor,
        iconTint = iconTint,
        message = message,
        onClick = onClick
    )
}

@Composable
private fun SnackbarShape(
    model: SnackbarView,
    containerColor: Color,
    onContainerColor: Color,
    iconTint: Color,
    message: String?,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = containerColor
            ),
            modifier = Modifier
                .padding(16.dp)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Icon(
                    imageVector = model.image,
                    contentDescription = stringResource(id = string.icon_snackbar_content_description),
                    tint = iconTint,
                    modifier = model.iconModifier,
                )
                Text(
                    text = message ?: "",
                    color = onContainerColor,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
