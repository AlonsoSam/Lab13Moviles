package com.example.lab13.ui.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

enum class ScreenState { Loading, Content, Error }

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContent(onBack: () -> Unit) {
    var state by remember { mutableStateOf(ScreenState.Loading) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { state = ScreenState.Loading }) { Text("Cargando") }
            Button(onClick = { state = ScreenState.Content }) { Text("Contenido") }
            Button(onClick = { state = ScreenState.Error }) { Text("Error") }
            Button(onClick = onBack) { Text("Volver") }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = state,
                transitionSpec = {
                    fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(300))
                },
                label = "ScreenStateTransition"
            ) { target ->
                when (target) {
                    ScreenState.Loading -> {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "Procesando la solicitud...",
                                modifier = Modifier.padding(8.dp),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    ScreenState.Content -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "Datos obtenidos",
                                modifier = Modifier.padding(8.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Bienvenido al panel de usuario",
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                    ScreenState.Error -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "Fallo de conexión",
                                modifier = Modifier.padding(8.dp),
                                color = androidx.compose.ui.graphics.Color.Red,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Verifique su red e intente nuevamente",
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}