package com.example.lab13.ui.theme

import androidx.compose.animation.core.animateDpAsState
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
fun SizePosition(onBack: () -> Unit) {

    var moved by remember { mutableStateOf(false) }

    val animatedSize by animateDpAsState(
        targetValue = if (moved) 250.dp else 80.dp,
        animationSpec = tween(750),
        label = "sizeAnim"
    )

    val animatedOffset by animateDpAsState(
        targetValue = if (moved) 150.dp else 0.dp,
        animationSpec = tween(750),
        label = "offsetAnim"
    )

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { moved = !moved }) {
                Text("Mover / Cambiar tamaño")
            }
            Button(onClick = onBack) {
                Text("Volver")
            }
        }

        Box(
            modifier = Modifier
                .offset(x = animatedOffset, y = animatedOffset)
                .size(animatedSize)
                .background(Color(0xFF00BCD4))
        )
    }
}