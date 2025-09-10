package com.sergio.saneia.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.withStyle

@Composable
fun HomeScreen(innerPadding: PaddingValues = PaddingValues()) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // ---------- TÍTULO ----------
            Text(
                text = "Pesquisa",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            // ---------- INTRODUÇÃO ----------
            Text(
                text = "O saneamento básico é um direito fundamental garantido pela Constituição Federal " +
                        "e regulamentado pela Lei nº 11.445, de 5 de janeiro de 2007, que estabelece " +
                        "diretrizes nacionais para garantir abastecimento de água potável, " +
                        "coleta e tratamento de esgoto, manejo adequado de resíduos sólidos " +
                        "e drenagem urbana. Apesar disso, muitas regiões brasileiras ainda sofrem " +
                        "com a ausência desses serviços essenciais.",
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
            )

            Divider(color = MaterialTheme.colorScheme.outlineVariant)

            // ---------- CONTEXTO LOCAL ----------
            Text(
                text = "Juazeiro do Norte – CE",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            )

            Text(
                text = "Na Rua Vicente Oliveira de Brito, no bairro Planalto, moradores convivem diariamente " +
                        "com a falta de infraestrutura: esgoto correndo a céu aberto, interrupções no fornecimento " +
                        "de água, ausência de pavimentação e pontos de acúmulo de lixo. Esses problemas " +
                        "afetam não apenas a saúde pública, mas também a dignidade e a qualidade de vida da comunidade.",
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
            )

            // ---------- DADOS DA PESQUISA ----------
            Text(
                text = "Principais dados levantados:",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("→ 70% dos moradores têm acesso à água encanada, mas sofrem com interrupções diárias.",
                    style = MaterialTheme.typography.bodySmall)
                Text("→ 0% possuem rede de esgoto – os dejetos são descartados a céu aberto.",
                    style = MaterialTheme.typography.bodySmall)
                Text("→ 60% contam com coleta regular de lixo, mas ainda há pontos de descarte irregular.",
                    style = MaterialTheme.typography.bodySmall)
                Text("→ A falta de pavimentação deixa a rua com lama no inverno e poeira no verão.",
                    style = MaterialTheme.typography.bodySmall)
            }

            Divider(color = MaterialTheme.colorScheme.outlineVariant)

            // ---------- IMPACTOS ----------
            Text(
                text = "Impactos observados",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            )

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("• Proliferação de doenças como diarreia, leptospirose, verminoses e arboviroses.",
                    style = MaterialTheme.typography.bodyMedium)
                Text("• Contaminação do solo e risco de poluição de lençóis freáticos.",
                    style = MaterialTheme.typography.bodyMedium)
                Text("• Aumento da exclusão social e redução da qualidade de vida.",
                    style = MaterialTheme.typography.bodyMedium)
            }

            Text(
                text = "Discussão",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )

            Text(
                text = "A realidade da comunidade evidencia que a ausência de saneamento básico não é apenas " +
                        "um problema técnico ou de infraestrutura, mas também social e ambiental. " +
                        "Sem esgoto tratado, água regular e ruas pavimentadas, a população fica mais exposta " +
                        "a riscos de saúde, exclusão econômica e degradação ambiental.",
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
            )

            Text(
                text = "Conclusão",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Garantir saneamento básico é garantir dignidade. A Lei nº 11.445/2007 já define " +
                        "os princípios e diretrizes, mas sua efetivação depende de investimentos consistentes " +
                        "e políticas públicas que priorizem comunidades em vulnerabilidade, como a Rua Vicente " +
                        "Oliveira de Brito, em Juazeiro do Norte – CE.",
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp)
            )

            Divider(color = MaterialTheme.colorScheme.outlineVariant, thickness = 1.dp)

            Text(
                text = "Referências",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

            val annotatedLink = buildAnnotatedString {
                pushStringAnnotation(
                    tag = "URL",
                    annotation = "https://www.planalto.gov.br/ccivil_03/_Ato2007-2010/2007/Lei/L11445compilado.htm"
                )
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append("Lei nº 11.445/2007")
                }
                pop()
            }

            ClickableText(
                text = annotatedLink,
                onClick = { offset ->
                    annotatedLink.getStringAnnotations(tag = "URL", start = offset, end = offset)
                        .firstOrNull()?.let { annotation ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                            context.startActivity(intent)
                        }
                }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
