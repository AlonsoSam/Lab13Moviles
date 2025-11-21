package com.example.lab13.ui.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedVisibility(onBack: () -> Unit) {

    var visible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Button(onClick = { visible = !visible }) {
                Text(text = if (visible) "Ocultar cuadro" else "Mostrar cuadro")
            }

            Button(onClick = onBack) {
                Text("Volver")
            }
        }

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(400)) + slideInVertically { -50 },
            exit = fadeOut(tween(300)) + slideOutVertically { 50 }
        ) {
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .background(Color(0xFFFF8F00)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Cuadro animado",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}