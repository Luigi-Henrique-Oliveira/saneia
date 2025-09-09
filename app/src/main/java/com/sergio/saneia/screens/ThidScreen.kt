package com.sergio.saneia.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class Question(
    val text: String,
    val options: List<String>,
    val correctIndex: Int
)

@Composable
fun ThirdScreen() {
    QuizScreen()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuizScreen() {
    val questions = remember {
        listOf(
            Question(
                text = "Qual é a principal dificuldade de saneamento encontrada na comunidade?",
                options = listOf(
                    "Esgoto a céu aberto prejudicando saúde e ambiente",
                    "Excesso de árvores nas calçadas",
                    "Alto custo das contas de energia elétrica",
                    "Falta de iluminação pública em alguns trechos"
                ),
                correctIndex = 0
            ),
            Question(
                text = "De que forma a falta de água encanada impacta o dia a dia das famílias?",
                options = listOf(
                    "Dependência de poços ou reservatórios improvisados",
                    "Maior necessidade de transporte coletivo",
                    "Redução de áreas verdes disponíveis",
                    "Dificuldade em obter acesso à internet"
                ),
                correctIndex = 0
            ),
            Question(
                text = "Qual risco ambiental está associado à ausência de rede de esgoto?",
                options = listOf(
                    "Contaminação do solo e da água subterrânea",
                    "Poluição sonora mais intensa",
                    "Aumento da produção agrícola",
                    "Maior tráfego de veículos pesados"
                ),
                correctIndex = 0
            ),
            Question(
                text = "Por que ruas sem pavimentação causam transtornos para os moradores?",
                options = listOf(
                    "Acúmulo de poeira no período seco e lama em dias de chuva",
                    "Dificuldade de acesso ao comércio eletrônico",
                    "Diminuição da rede de energia elétrica",
                    "Excesso de áreas destinadas ao lazer"
                ),
                correctIndex = 0
            ),
            Question(
                text = "O descarte incorreto do lixo pode resultar em:",
                options = listOf(
                    "Proliferação de insetos e roedores",
                    "Aumento da arrecadação de impostos",
                    "Diminuição da densidade populacional",
                    "Melhoria automática da qualidade do ar"
                ),
                correctIndex = 0
            ),
            Question(
                text = "Além de obras, qual iniciativa pode melhorar a situação do bairro?",
                options = listOf(
                    "Campanhas de educação ambiental com participação da comunidade",
                    "Construção de mais estacionamentos particulares",
                    "Ampliação de áreas de lazer privadas",
                    "Aumento do número de shoppings"
                ),
                correctIndex = 0
            )
        )
    }

    val total = questions.size
    var currentIndex by rememberSaveable { mutableStateOf(0) }
    var score by rememberSaveable { mutableStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(selectedOption) {
        if (selectedOption != null && !showResult) {
            delay(1200)
            if (selectedOption == questions[currentIndex].correctIndex) {
                score++
            }
            if (currentIndex < total - 1) {
                currentIndex++
                selectedOption = null
            } else {
                showResult = true
            }
        }
    }

    val progress = (currentIndex + 1).toFloat() / total.toFloat()
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!showResult) {
            AnimatedContent(
                targetState = currentIndex,
                transitionSpec = {
                    slideInHorizontally(tween(300)) + fadeIn() with
                            slideOutHorizontally(tween(300)) + fadeOut()
                },
                modifier = Modifier.weight(1f)
            ) { index ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "Pergunta ${index + 1} de $total",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = questions[index].text,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    items(questions[index].options.size) { optIdx ->
                        OptionItem(
                            text = questions[index].options[optIdx],
                            index = optIdx,
                            selected = selectedOption,
                            correctIndex = questions[index].correctIndex,
                            onClick = { if (selectedOption == null) selectedOption = optIdx }
                        )
                    }
                }
            }
        } else {
            ResultCard(
                score = score,
                total = total,
                onRestart = {
                    currentIndex = 0
                    score = 0
                    selectedOption = null
                    showResult = false
                }
            )
        }
    }
}

@Composable
fun OptionItem(
    text: String,
    index: Int,
    selected: Int?,
    correctIndex: Int,
    onClick: () -> Unit
) {
    val reveal = selected != null
    val isSelected = selected == index
    val isCorrect = index == correctIndex

    val bgColor = when {
        reveal && isSelected && isCorrect -> MaterialTheme.colorScheme.primaryContainer
        reveal && isSelected && !isCorrect -> MaterialTheme.colorScheme.errorContainer
        reveal && !isSelected && isCorrect -> MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val symbol = when {
        reveal && isSelected && isCorrect -> "✔"
        reveal && isSelected && !isCorrect -> "✖"
        reveal && !isSelected && isCorrect -> "✔"
        else -> ""
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor)
            .clickable(enabled = !reveal) { onClick() }
            .padding(14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${'A' + index})",
                modifier = Modifier.width(28.dp),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, modifier = Modifier.weight(1f))
            if (symbol.isNotEmpty()) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = symbol,
                    fontWeight = FontWeight.Bold,
                    color = if (isCorrect) Color(0xFF1B5E20) else MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun ResultCard(score: Int, total: Int, onRestart: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seus Acertos",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Você acertou $score de $total",
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRestart) {
                Text("Refazer Quiz")
            }
        }
    }
}
