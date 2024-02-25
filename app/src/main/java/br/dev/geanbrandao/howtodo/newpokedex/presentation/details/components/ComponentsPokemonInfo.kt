package br.dev.geanbrandao.howtodo.newpokedex.presentation.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.common.PokemonNumberName
import br.dev.geanbrandao.howtodo.newpokedex.presentation.evolutions.EvolutionChainScreen
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.Bulbasaur
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.components.PokemonTypesView
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonDetailsModel
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.Black
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingFour
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingThree
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PaddingTwo
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextBodyLarge
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.TextTitleExtraLarge

@Composable
fun PokemonDetailsInfo(
    modifier: Modifier = Modifier,
    pokemon: PokemonDetailsModel,
) {
    PokemonDetailsInfoView(modifier = modifier, item = pokemon)
}

@Composable
private fun PokemonDetailsInfoView(
    modifier: Modifier = Modifier,
    item: PokemonDetailsModel = Bulbasaur
) {
    Column(
        modifier = modifier
            .padding(horizontal = PaddingTwo)
    ) {
        PokemonName(
            text = item.pokemon.name,
            fontSize = TextTitleExtraLarge,
        )
        PokemonNumberName(
            text = item.pokemon.numberName,
            fontSize = TextBodyLarge,
        )
        Spacer(modifier = Modifier.size(PaddingTwo))
        PokemonTypesView(
            typeOne = item.pokemon.typeOne,
            typeTwo = item.pokemon.typeTwo,
            isLarge = true
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = PaddingThree),
            color = Black.copy(alpha = 0.05f)
        )
        PokemonFeaturesView(item = item)
        Spacer(modifier = Modifier.size(size = PaddingThree))
        GenderInfo(genderRate = item.genderRate)
        Spacer(modifier = Modifier.size(size = PaddingFour))
//        PokemonWeakness(list = getPokemonWeakness(typeOne = item.pokemon.typeOne, typeTwo = item.pokemon.typeTwo))
        PokemonStats(stats = item.pokemon.stats, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(size = PaddingFour))
        EvolutionChainScreen(
            chainUrl = item.evolutionUrl,
            modifier = Modifier.fillMaxWidth().padding(bottom = PaddingFour),
        )
    }
}

@Composable
private fun PokemonFeaturesView(
    item: PokemonDetailsModel = Bulbasaur
) {
    val weight = buildString {
        append(item.pokemon.weight)
        append(" kg")
    }
    val height = buildString {
        append(item.pokemon.height)
        append(" m")
    }

    Column(Modifier.fillMaxWidth()) {
        Row {
            PokemonFeature(
                iconId = R.drawable.ic_weight,
                labelTextId = R.string.pokemon_feature_label_weight,
                infoText = weight,
                modifier = Modifier.weight(weight = 1f),
            )
            Spacer(modifier = Modifier.size(size = PaddingThree))
            PokemonFeature(
                iconId = R.drawable.ic_height,
                labelTextId = R.string.pokemon_feature_label_height,
                infoText = height,
                modifier = Modifier.weight(weight = 1f),
            )
        }
        Spacer(modifier = Modifier.size(size = PaddingTwo))
        Row {
            PokemonFeature(
                iconId = R.drawable.ic_category,
                labelTextId = R.string.pokemon_feature_label_generation,
                infoText = item.generation,
                modifier = Modifier.weight(weight = 1f),
            )
            Spacer(modifier = Modifier.size(size = PaddingThree))
            PokemonFeature(
                iconId = R.drawable.ic_pokeball,
                labelTextId = R.string.pokemon_feature_label_ability,
                infoText = item.pokemon.abilities.firstOrNull().orEmpty(),
                modifier = Modifier.weight(weight = 1f),
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PokemonFeaturesPreview() {
    PokemonFeaturesView()
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailsInfoPreview() {
    PokemonDetailsInfoView()
}