package com.example.lab13.ui.theme

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab13.R
import androidx.compose.foundation.layout.statusBarsPadding

private val BRAWLER_SIZE_NORMAL = 80.dp
private val BRAWLER_SIZE_SUPER = 110.dp
private val PROJECTILE_OFFSET_END = 300.dp

@Composable
fun BrawlerPrototype(onBack: () -> Unit) {
    // 1. Estados
    var isSuperCharged by remember { mutableStateOf(false) }
    var showProjectile by remember { mutableStateOf(false) }
    var projectileKey by remember { mutableIntStateOf(0) }

    val brawlerSize by animateDpAsState(
        targetValue = if (isSuperCharged) BRAWLER_SIZE_SUPER else BRAWLER_SIZE_NORMAL,
        animationSpec = tween(300),
        label = "brawlerSizeAnim"
    )

    val brawlerColor by animateColorAsState(
        targetValue = if (isSuperCharged) Color(0xFFE53935) else Color(0xFFFFB300), // Rojo Super / Naranja Normal
        animationSpec = tween(300),
        label = "brawlerColorAnim"
    )

    val projectileOffset by animateDpAsState(
        // 🎯 DISPARO HACIA ADELANTE (300.dp)
        targetValue = if (showProjectile) PROJECTILE_OFFSET_END else 0.dp,
        animationSpec = tween(durationMillis = 350),
        label = "projectileOffsetAnim"
    )

    val launchAttack: () -> Unit = {
        if (isSuperCharged) {
            showProjectile = true
            projectileKey++

            object : Thread() {
                override fun run() {
                    sleep(400)
                    showProjectile = false
                    isSuperCharged = false
                }
            }.start()
        } else {
            isSuperCharged = true
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.arena_background),
            contentDescription = "Game Arena Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { launchAttack() }) {
                    Text(if (isSuperCharged) "Lanzar Ataque" else "Cargar Súper")
                }
                Button(onClick = onBack) { Text("Volver") }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.CenterStart
            ) {

                key(projectileKey) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = showProjectile,
                        enter = fadeIn(tween(100)),
                        exit = fadeOut(tween(50))
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .offset(x = projectileOffset + BRAWLER_SIZE_NORMAL / 2, y = 0.dp)
                                .clip(CircleShape)
                                .background(Color.Yellow) // La bala amarilla que se desliza
                                .align(Alignment.CenterStart)
                                .offset(x = 20.dp)
                        )
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.colt_icon),
                    contentDescription = "Colt Icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(brawlerSize)
                        .clip(CircleShape)
                        .background(brawlerColor.copy(alpha = 0.5f))
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                launchAttack()
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Toque el BRAWLER para cargar/lanzar el SUPER.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}