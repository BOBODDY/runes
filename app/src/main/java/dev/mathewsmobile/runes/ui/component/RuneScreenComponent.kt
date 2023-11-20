package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mathewsmobile.runes.UiState
import dev.mathewsmobile.runes.data.TestData
import dev.mathewsmobile.runes.ui.theme.RunesTheme
import java.net.URL

@Composable
fun RuneScreenComponent(modifier: Modifier, runeState: UiState?, onBackClick: () -> Unit) {
    if (runeState == null) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Default.Close, contentDescription = "Close", modifier = modifier
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

            Icon(
                Icons.Default.ArrowBack, contentDescription = "Back", modifier = modifier
                .clickable { onBackClick() }
                .padding(16.dp))
        }
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