package dev.mathewsmobile.runes.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.mathewsmobile.runes.RuneViewModel

object RuneScreen {
    const val NavRoute = "rune"
}

@Composable
fun RuneScreen(
    modifier: Modifier = Modifier,
    viewModel: RuneViewModel,
    navController: NavController,
) {
    val runeState by viewModel.uiState.collectAsState()
    RuneScreenComponent(modifier = modifier, runeState) {
        navController.popBackStack()
    }
}
