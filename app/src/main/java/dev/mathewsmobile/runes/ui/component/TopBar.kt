package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(modifier: Modifier, onInfoClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = modifier.width(16.dp))
        Text(text = "Runes", modifier = modifier.weight(1f), fontSize = 24.sp, fontWeight = FontWeight.Bold)
        IconButton(onClick = { onInfoClick() }) {
            Icon(Icons.Default.Info, contentDescription = "View about page")
        }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(Modifier, {})
}