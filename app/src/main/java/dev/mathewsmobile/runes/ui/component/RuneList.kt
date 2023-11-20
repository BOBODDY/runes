package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mathewsmobile.runes.data.Rune
import dev.mathewsmobile.runes.data.TestData
import dev.mathewsmobile.runes.ui.theme.RunesTheme

@Composable
fun RuneList(
    modifier: Modifier,
    runes: List<Rune>,
    onSearchEntered: (String) -> Unit,
    onClick: (Rune) -> Unit,
    onInfoClick: () -> Unit,
) {
    var searchPhrase by remember { mutableStateOf("") }
    Column {
        TopBar(modifier = modifier, onInfoClick = { onInfoClick() })
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            placeholder = { Text("Search") },
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

@Preview
@Composable
private fun RunePreview() {
    RunesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RuneList(Modifier, TestData.runes + TestData.runes, {}, {}, {})
        }
    }
}