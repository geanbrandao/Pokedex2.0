package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.dev.geanbrandao.howtodo.newpokedex.common.capitalize
import br.dev.geanbrandao.howtodo.newpokedex.common.preview.PokemonDetailsPreviewProvider
import br.dev.geanbrandao.howtodo.newpokedex.presentation.evolutions.EvolutionChainScreen
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonType
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.Feature
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2Details
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.AppTheme
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.Black
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IconTypeLargeSize
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingFour
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingHalf
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingOne
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingThree
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo

@Composable
fun PokemonDetailsInfo(
    modifier: Modifier = Modifier,
    pokemon: PokemonV2Details,
) {
    PokemonDetailsInfoView(modifier = modifier, item = pokemon)
}

@Composable
private fun PokemonDetailsInfoView(
    modifier: Modifier = Modifier,
    item: PokemonV2Details,
) {
    Column(
        modifier = modifier
            .padding(horizontal = PaddingTwo)
    ) {
        PokemonBasicInfo(item = item)

        PokemonFeaturesView(item = item)

        Spacer(modifier = Modifier.size(size = PaddingThree))
        GenderInfo(genderRate = item.genderRate)

        Spacer(modifier = Modifier.size(size = PaddingFour))
//        PokemonWeakness(list = getPokemonWeakness(typeOne = item.pokemon.typeOne, typeTwo = item.pokemon.typeTwo))
        PokemonStats(stats = item.pokemon.stats, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.size(size = PaddingFour))
        EvolutionChainScreen(
            evolutions = item.evolutions,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = PaddingFour),
        )
    }
}

@Composable
private fun PokemonBasicInfo(item: PokemonV2Details) {
    Text(
        text = item.pokemon.name.capitalize(),
        style = MaterialTheme.typography.titleLarge,
    )
    Text(
        text = item.pokemon.numberFormatted,
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = Modifier.size(PaddingTwo))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PokemonType(
            type = item.pokemon.typeOne,
            iconSize = IconTypeLargeSize,
            style = MaterialTheme.typography.bodyLarge,
        )
        item.pokemon.typeTwo?.let {
            Spacer(modifier = Modifier.size(PaddingOne))
            PokemonType(
                type = it,
                iconSize = IconTypeLargeSize,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PaddingThree),
        color = Black.copy(alpha = 0.05f)
    )
}

@Composable
private fun PokemonFeaturesView(
    item: PokemonV2Details
) {
    Column(Modifier.fillMaxWidth()) {
        Row {
            PokemonFeature(
                item = Feature.weight.copy(infoText = item.pokemon.weightFormatted),
                modifier = Modifier.weight(weight = 1f),
            )
            Spacer(modifier = Modifier.size(size = PaddingThree))
            PokemonFeature(
                item = Feature.height.copy(infoText = item.pokemon.heightFormatted),
                modifier = Modifier.weight(weight = 1f),
            )
        }
        Spacer(modifier = Modifier.size(size = PaddingTwo))
        Row {
            PokemonFeature(
                item = Feature.generation.copy(infoText = item.pokemon.generationFormatted),
                modifier = Modifier.weight(weight = 1f),
            )
            Spacer(modifier = Modifier.size(size = PaddingThree))
            PokemonFeature(
                item = Feature.ability.copy(infoText = item.pokemon.abilities.firstOrNull().orEmpty()),
                modifier = Modifier.weight(weight = 1f),
            )
        }
    }
}

@Composable
fun PokemonFeature(
    item: Feature,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.size(PaddingOne))
            Text(
                text = stringResource(id = item.labelTextId),
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Spacer(modifier = Modifier.size(size = PaddingHalf))
        Card(
            modifier = Modifier.fillMaxWidth(),

        ) {
            Text(
                text = item.infoText.capitalize(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = PaddingOne),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .border(
//                        shape = RoundedCornerShape(15.dp),
//                        width = 1.dp,
//                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
//                    )
//                    .padding(all = PaddingOne),
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonFeaturePreview(
    @PreviewParameter(PokemonFeaturePreviewProvider::class) item: Feature,
) {
    AppTheme {
        PokemonFeature(item)
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonFeaturesPreview(
    @PreviewParameter(PokemonDetailsPreviewProvider::class) item: PokemonV2Details
) {
    AppTheme {
        PokemonFeaturesView(item)
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonBasicInfoPreview(
    @PreviewParameter(PokemonDetailsPreviewProvider::class) item: PokemonV2Details
) {
    AppTheme {
        PokemonBasicInfo(item = item)
    }
}

class PokemonFeaturePreviewProvider: PreviewParameterProvider<Feature> {
    override val values: Sequence<Feature>
        get() = sequenceOf(
            Feature.weight.copy(infoText = "6.9 kg"),
            Feature.height.copy(infoText = "17.7 cm"),
            Feature.generation.copy(infoText = "I"),
            Feature.ability.copy(infoText = "Overgrow"),
        )
}