@file:OptIn(ExperimentalMaterial3Api::class)

package dev.mathewsmobile.runes.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

object AboutScreen {
    const val NavRoute = "about"
}

@Composable
fun AboutScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("About this app") }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
                }
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("ᚠ", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Runes", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier
                .clickable {
                           uriHandler.openUri("https://mathewsmobile.dev/privacy")
                }, verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Privacy policy",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(8.dp),
                    textDecoration = TextDecoration.Underline
                )
                Icon(Icons.Default.ExitToApp, modifier = Modifier.size(12.dp), contentDescription = "Privacy policy link")
            }
        }
    }
}

@Preview
@Composable
private fun AboutPreview() {
    AboutScreen(rememberNavController())
}