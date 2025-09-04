package com.sergio.saneia.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AppTypography {
    val headline = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1A1A1A)
    )

    val body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color(0xFF333333)
    )

    val note = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF666666)
    )
}


@Composable
fun HomeScreen(){
    HomeCard()
}
@Composable
fun HomeCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Direito ao Saneamento Básico",
                style = AppTypography.headline
            )

            Text(
                text = "Todo cidadão tem direito a água limpa, coleta e tratamento de esgoto, " +
                        "gestão adequada de resíduos e drenagem segura contra enchentes.",
                style = AppTypography.body
            )

            Text(
                text = "Esses serviços devem ser prestados com qualidade, continuidade, " +
                        "transparência e participação social.",
                style = AppTypography.body
            )

            Divider(Modifier.padding(vertical = 4.dp))

            Text(
                text = "Lei nº 11.445/2007 (Marco Legal do Saneamento)",
                style = AppTypography.note
            )
        }
    }
}


