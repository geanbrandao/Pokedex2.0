package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.TextLabel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.capitalize
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel.Stat
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTiny
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.StatColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextBodyLarge
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelLarge

@Composable
fun PokemonStats(
    stats: List<Stat>,
    modifier: Modifier = Modifier,
) {
    PokemonStatsView(stats = stats, modifier = modifier)
}

@Composable
private fun PokemonStatsView(
    modifier: Modifier = Modifier,
    stats: List<Stat> = listOf(
        Stat("HP", 65),
        Stat("Atacck", 10),
        Stat("Defense", 50),
    ),
) {

    val maxValue: Int = stats.maxOf { it.value }

    Column(modifier = modifier) {
        PokemonName(
            text = stringResource(R.string.pokemon_details_label_stats),
            fontSize = TextLabelLarge,
        )
        Spacer(modifier = Modifier.size(size = PaddingTwo))
        stats.forEach {
            val progress = (it.value.toFloat() / maxValue.toFloat())
            StatItemView(statValue = it.value, statProgress = progress, name = it.name)
            Spacer(modifier = Modifier.size(PaddingOne))
        }
    }
}

@Composable
private fun StatItemView(
    statValue: Int,
    statProgress: Float,
    name: String,
) {
    val initialProgress = remember { mutableFloatStateOf(0f) }
    val progress = animateFloatAsState(
        targetValue = initialProgress.floatValue,
        label = "Stat progress animation",
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(statProgress) {
        initialProgress.floatValue = statProgress
    }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextLabel(text = name.capitalize(), fontSize = TextBodyLarge)
            TextLabel(text = statValue.toString(), fontSize = TextBodyLarge)
        }
        Spacer(modifier = Modifier.size(PaddingTiny))
        LinearProgressIndicator(
            progress = progress.value,
            color = StatColor,
            trackColor = StatColor.copy(alpha = 0.5f),
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .height(PaddingTwo)
                .fillMaxWidth(),
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PokemonStatsPreview() {
    PokemonStatsView()
}