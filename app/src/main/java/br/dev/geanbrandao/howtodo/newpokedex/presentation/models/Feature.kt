package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.dev.geanbrandao.howtodo.newpokedex.R

data class Feature(
    @DrawableRes val iconId: Int,
    @StringRes val labelTextId: Int,
    val infoText: String,
) {

    companion object {
        val weight = Feature(
            iconId = R.drawable.ic_weight,
            labelTextId = R.string.pokemon_feature_label_weight,
            infoText = "",
        )

        val height = Feature(
            iconId = R.drawable.ic_height,
            labelTextId = R.string.pokemon_feature_label_height,
            infoText = "",
        )

        val generation = Feature(
            iconId = R.drawable.ic_category,
            labelTextId = R.string.pokemon_feature_label_generation,
            infoText = "",
        )

        val ability = Feature(
            iconId = R.drawable.ic_pokeball,
            labelTextId = R.string.pokemon_feature_label_ability,
            infoText = "",
        )
    }
}
