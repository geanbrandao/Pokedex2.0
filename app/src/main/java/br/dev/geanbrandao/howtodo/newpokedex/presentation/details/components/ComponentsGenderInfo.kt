package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.FemaleColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.MaleColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo

@Composable
fun GenderInfo(
    genderRate: Int,
    modifier: Modifier = Modifier,
) {
    val (maleProbability, femaleProbability) = calculateGenderProbability(genderRate)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.pokemon_gender_info_label),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.size(PaddingOne))
        LinearProgressIndicator(
            progress = { maleProbability / 100f },
            color = MaleColor,
            trackColor = FemaleColor,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .fillMaxWidth()
                .height(PaddingTwo)
        )
        Spacer(modifier = Modifier.size(PaddingHalf))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextGenderPercentView(genderIconId = R.drawable.ic_male, percent = maleProbability)
            TextGenderPercentView(genderIconId = R.drawable.ic_female, percent = femaleProbability)
        }
    }
}

@Composable
private fun TextGenderPercentView(
    @DrawableRes genderIconId: Int,
    percent: Float,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = genderIconId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
        )
        Text(
            text = "$percent%",
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenderInfoPreview(
    @PreviewParameter(GenderInfoPreviewProvider::class) genderRate: Int,
) {
    AppTheme {
        GenderInfo(genderRate)
    }
}

class GenderInfoPreviewProvider : PreviewParameterProvider<Int> {
    override val values: Sequence<Int>
        get() = sequenceOf(1, 2, 3, 6)
}

fun calculateGenderProbability(genderRate: Int): Pair<Float, Float> {
    val male = ((8 - genderRate) / 8.0 * 100.0).toFloat()
    val female = (100.0 - male).toFloat()
    return Pair(male, female)
}
