package dev.mathewsmobile.runes.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun RandomRuneFab(onFabClick: () -> Unit) {
    FloatingActionButton(onClick = { onFabClick() }) {
        Icon(Icons.Default.Refresh, contentDescription = "Pick a random rune")
    }
}
