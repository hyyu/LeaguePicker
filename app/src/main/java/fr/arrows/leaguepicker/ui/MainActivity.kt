package fr.arrows.leaguepicker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.arrows.leaguepicker.home.compose.Home
import fr.arrows.leaguepicker.navigation.Screen
import fr.arrows.leaguepicker.ui.theme.LeaguePickerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeaguePickerTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(
                        navController = navController,
                    )
                }
            }
        }
    }

    @Composable
    private fun Navigation(navController: NavHostController, ) {
        NavHost(
            navController = navController,
            route = Screen.Root.value,
            startDestination = Screen.Home.value
        ) {
            composable(Screen.Home.value) {
                Home()
            }
        }
    }
}
