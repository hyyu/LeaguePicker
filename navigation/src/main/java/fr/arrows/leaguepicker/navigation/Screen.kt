package fr.arrows.leaguepicker.navigation

import fr.arrows.leaguepicker.navigation.values.ScreenValues

sealed class Screen(val value: String) {
    data object Home : Screen(ScreenValues.HOME)
}
