package dev.mathewsmobile.runes.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onInfoClick: () -> Unit) {
    TopAppBar(
        title = { Text("Runes") },
        actions = { IconButton(onClick = { onInfoClick() }) {
            Icon(Icons.Default.Info, contentDescription = "More details")
        }}
    )
}