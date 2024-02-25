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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.TextLabel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.FemaleColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.MaleColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextLabelSmall

@Composable
fun GenderInfo(
    genderRate: Int,
    modifier: Modifier = Modifier,
) {
    GenderInfoView(
        genderRate = genderRate,
        modifier = modifier
    )
}

@Composable
private fun GenderInfoView(
    modifier: Modifier = Modifier,
    genderRate: Int = 1,
) {
    val (maleProbability, femaleProbability) = calculateGenderProbability(genderRate)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        TextLabel(
            text = "GÊNERO",
            fontSize = TextLabelSmall,
        )
        Spacer(modifier = Modifier.size(PaddingOne))
        LinearProgressIndicator(
            progress = maleProbability / 100f,
            color = MaleColor,
            trackColor = FemaleColor,
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .fillMaxWidth()
                .height(PaddingOne)
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
            tint = Color.Unspecified
        )
        TextLabel(text = "$percent%", fontSize = TextLabelSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun GenderInfoPreview() {
    GenderInfoView()
}

fun calculateGenderProbability(genderRate: Int): Pair<Float, Float> {
    val male = ((8 - genderRate) / 8.0 * 100.0).toFloat()
    val female = (100.0 - male).toFloat()
    return Pair(male, female)
}
