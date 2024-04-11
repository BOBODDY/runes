package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.mathewsmobile.runes.RunesListViewModel
import kotlinx.coroutines.launch

object RuneList {
    const val NavRoute = "RuneList"
}

@Composable
fun RuneListScreen(modifier: Modifier = Modifier, viewModel: RunesListViewModel, navController: NavController) {
    val uiState by viewModel.uiStateFlow.collectAsState()
    val runes = uiState.runes

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            RandomRuneFab {
                coroutineScope.launch {
                    val randomRune = viewModel.getRandomRune()
                    val route = "${RuneScreen.NavRoute}/${randomRune?.id}"
                    navController.navigate(route)
                }
            }
        }
    ) { paddingValues ->
        RuneList(
            modifier = modifier.padding(paddingValues),
            runes = runes.runes,
            viewModel::search,
            onInfoClick = { navController.navigate(AboutScreen.NavRoute) },
            onClick = { rune ->
                val route = "${RuneScreen.NavRoute}/${rune.id}"
                navController.navigate(route)
            }
        )
    }
}
