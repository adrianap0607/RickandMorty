package com.example.laboratorio9.presentation.mainFlow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio9.ui.theme.laboratorio9Theme


@Composable
fun ErrorScreen(
    errorMessage: String = "Error al obtener listado de personajes. Intenta de nuevo",
    onRetry: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {

            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Error Icon",
                tint = Color(0xFFB00020),
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 16.dp)
            )


            Text(
                text = errorMessage,
                color = Color(0xFFB00020),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )


            Button(
                onClick = onRetry,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFB00020)
                ),
                border = BorderStroke(1.dp, Color.Gray),
                modifier = Modifier
                    .padding(8.dp)
                    .defaultMinSize(minWidth = 100.dp, minHeight = 32.dp)
            ) {
                Text(
                    text = "Reintentar",
                    color = Color(0xFFB00020),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    laboratorio9Theme {
        ErrorScreen()
    }
}

