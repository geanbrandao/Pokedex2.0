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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.common.capitalize
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTiny
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.StatColor

@Composable
fun PokemonStats(
    stats: List<PokemonV2.Stat>,
    modifier: Modifier = Modifier,
) {

    val maxValue: Int = stats.maxOf { it.value }

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.pokemon_details_label_stats),
            style = MaterialTheme.typography.titleLarge,
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
            Text(
                text = name.capitalize(),
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = statValue.toString(),
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Spacer(modifier = Modifier.size(PaddingTiny))
        LinearProgressIndicator(
            progress = { progress.value },
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
private fun PokemonStatsPreview(
    @PreviewParameter(PokemonStatsPreviewProvider::class) stats: List<PokemonV2.Stat>
) {
    AppTheme {
        PokemonStats(stats)
    }
}

class PokemonStatsPreviewProvider : PreviewParameterProvider<List<PokemonV2.Stat>> {
    override val values: Sequence<List<PokemonV2.Stat>>
        get() = sequenceOf(
            listOf(
                PokemonV2.Stat("hp", 65),
                PokemonV2.Stat("attack", 10),
                PokemonV2.Stat("defense", 50),
            ),
            listOf(
                PokemonV2.Stat("hp", 70),
                PokemonV2.Stat("attack", 100),
                PokemonV2.Stat("defense", 80),
            ),
            listOf(
                PokemonV2.Stat("hp", 15),
                PokemonV2.Stat("attack", 20),
                PokemonV2.Stat("defense", 50),
                PokemonV2.Stat("special-attack", 100),
            ),
        )
}