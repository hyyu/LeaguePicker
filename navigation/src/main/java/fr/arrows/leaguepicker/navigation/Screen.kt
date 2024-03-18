package fr.arrows.leaguepicker.navigation

import fr.arrows.leaguepicker.navigation.values.ScreenValues

sealed class Screen(val value: String) {
    data object Root : Screen(ScreenValues.ROOT)
    data object Home : Screen(ScreenValues.HOME)

    companion object {
        fun mapValueToScreen(value: String?): Screen? = when (value) {
            Home.value -> Home
            Root.value -> Root
            else -> null
        }
    }

}
