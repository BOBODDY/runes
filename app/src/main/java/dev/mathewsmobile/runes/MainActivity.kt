package dev.mathewsmobile.runes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dev.mathewsmobile.runes.ui.component.RuneList
import dev.mathewsmobile.runes.ui.component.RuneListScreen
import dev.mathewsmobile.runes.ui.component.RuneScreen
import dev.mathewsmobile.runes.ui.theme.RunesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = RuneList.NavRoute) {
                        composable(RuneList.NavRoute) {
                            val viewModel by viewModels<RunesListViewModel>()
                            RuneListScreen(viewModel, navController)
                        }
                        composable(
                            "${RuneScreen.NavRoute}/{runeId}",
                            arguments = listOf(navArgument("runeId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val rune = backStackEntry.arguments?.getInt("runeId") ?: 0
                            val viewModel by viewModels<RuneViewModel>()
                            viewModel.setRune(rune)

                            RuneScreen(navController = navController, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
