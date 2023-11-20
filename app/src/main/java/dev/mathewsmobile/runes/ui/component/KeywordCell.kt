package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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