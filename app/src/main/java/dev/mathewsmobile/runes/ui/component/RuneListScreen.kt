package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.mathewsmobile.runes.RunesListViewModel

object RuneList {
    const val NavRoute = "RuneList"
}

@Composable
fun RuneListScreen(viewModel: RunesListViewModel, navController: NavController) {
    val uiState by viewModel.uiStateFlow.collectAsState()
    val runes = uiState.runes

    uiState.selectedRune?.let {
        val route = "${RuneScreen.NavRoute}/${it.id}"
        navController.navigate(route)
    }

    Scaffold(
        topBar = { TopBar {
            navController.navigate(AboutScreen.NavRoute)
        }},
        floatingActionButton = { RandomRuneFab(viewModel::getRandomRune) }
    ) {
        RuneList(
            modifier = Modifier.padding(it),
            runes = runes.runes,
            viewModel::search
        ) {
            val route = "${RuneScreen.NavRoute}/${it.id}"
            navController.navigate(route)
        }
    }
}
