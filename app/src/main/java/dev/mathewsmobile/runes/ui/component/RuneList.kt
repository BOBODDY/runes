package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.mathewsmobile.runes.data.Rune
import dev.mathewsmobile.runes.RunesListViewModel
import dev.mathewsmobile.runes.data.TestData
import dev.mathewsmobile.runes.ui.theme.RunesTheme

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

    Scaffold(floatingActionButton = { RandomRuneFab(viewModel::getRandomRune) }) {
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

@Composable
fun RandomRuneFab(onFabClick: () -> Unit) {
    FloatingActionButton(onClick = { onFabClick() }) {
        Icon(Icons.Default.Refresh, contentDescription = "Pick a random rune")
    }
}

@Composable
fun RuneList(
    modifier: Modifier,
    runes: List<Rune>,
    onSearchEntered: (String) -> Unit,
    onClick: (Rune) -> Unit,
) {
    var searchPhrase by remember { mutableStateOf("") }
    Column {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null)},
            placeholder = { Text("Search")},
            value = searchPhrase,
            onValueChange = {
                searchPhrase = it
                onSearchEntered(it)
            }
        )
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2)
        ) {
            items(runes.size) { index ->
                RuneCell(modifier = modifier, rune = runes[index], onClick = { onClick(it) })
            }
        }
    }
}

@Composable
fun RuneCell(modifier: Modifier, rune: Rune, onClick: (Rune) -> Unit) {
    Card(modifier = modifier
        .padding(horizontal = 4.dp, vertical = 2.dp)
        .clickable { onClick(rune) }
        .fillMaxWidth()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${rune.rune}", fontSize = 72.sp)
            Spacer(modifier = modifier.height(8.dp))
            Text(rune.name)
        }
    }
}

@Preview
@Composable
private fun RunePreview() {
    RunesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RuneList(Modifier, TestData.runes, {}, {})
        }
    }
}