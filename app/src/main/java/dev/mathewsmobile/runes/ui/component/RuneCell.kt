package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mathewsmobile.runes.data.Rune

@Composable
fun RuneCell(modifier: Modifier, rune: Rune, onClick: (Rune) -> Unit) {
    Card(modifier = modifier
        .padding(horizontal = 4.dp, vertical = 2.dp)
        .clickable(
        ) { onClick(rune) }
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
