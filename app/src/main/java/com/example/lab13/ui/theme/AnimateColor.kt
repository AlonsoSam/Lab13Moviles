package com.example.lab13.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimateColor(onBack: () -> Unit) {

    var isYellow by remember { mutableStateOf(false) }

    val animatedColor by animateColorAsState(
        targetValue = if (isYellow) Color(0xFFFFC107) else Color(0xFFD32F2F),
        animationSpec = tween(600),
        label = "colorAnim"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { isYellow = !isYellow }) {
                Text("Cambiar color")
            }
            Button(onClick = onBack) {
                Text("Volver")
            }
        }

        Box(
            modifier = Modifier
                .size(230.dp)
                .background(animatedColor)
        )
    }
}