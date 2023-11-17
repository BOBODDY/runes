package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.mathewsmobile.runes.RuneViewModel
import dev.mathewsmobile.runes.UiState
import dev.mathewsmobile.runes.data.Rune
import dev.mathewsmobile.runes.data.TestData
import dev.mathewsmobile.runes.ui.theme.RunesTheme
import java.net.URL

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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RuneScreenComponent(modifier: Modifier, runeState: UiState?, onBackClick: () -> Unit) {
    if (runeState == null) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close", modifier = modifier
                .clickable { onBackClick() }
                .padding(8.dp))
            Text(text = "Something went wrong...")
        }
        return
    } else {
        Box {
            Column(
                modifier = modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "${runeState.rune}",
                    fontSize = 72.sp
                )
                Text(
                    text = runeState.name,
                    fontSize = 36.sp
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = runeState.description,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = modifier.height(16.dp))

                Text(text = "Keywords:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                KeywordList(modifier = modifier, keywords = runeState.keywords)
            }

            Icon(Icons.Default.ArrowBack, contentDescription = "Back", modifier = modifier
                .clickable { onBackClick() }
                .padding(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KeywordList(modifier: Modifier, keywords: List<String>) {

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        keywords.map {
            KeywordCell(modifier = modifier, keyword = it)
        }
    }
}

@Composable
fun KeywordCell(modifier: Modifier, keyword: String) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray.copy(alpha = 0.33f))
    ) {
        Text(modifier = modifier.padding(8.dp), text = keyword)
    }
}

@Preview
@Composable
private fun KeywordPreview() {
    Surface(
        color = Color.White
    ) {
        KeywordCell(Modifier, "Keyword")
    }

}

@Preview
@Composable
private fun RuneScreenPreview() {
    val uiState = UiState(
        TestData.runes[0].rune,
        TestData.runes[0].name,
        TestData.runes[0].description,
        TestData.runes[0].keywords,
        URL("https://en.wikipedia.org/wiki/Fehu")
    )
    RunesTheme {
        // A surface container using the 'background' color from the theme
        Column {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                RuneScreenComponent(Modifier, uiState) {}
            }
        }
    }
}