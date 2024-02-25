package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import br.dev.geanbrandao.howtodo.newpokedex.R
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeEnum.WATER
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.BugColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.DarkColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.DragonColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.ElectricColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.FairyColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.FightingColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.FireColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.FlyingColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.GhostColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.GrassColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.GroundColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.IceColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.NormalColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PoisonColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.PsychicColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.RockColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.SteelColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.WaterColor
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
sealed class PokemonTypeModel(
    val identifier: PokemonTypeEnum,
    val color: @RawValue Color,
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
) : Parcelable {

    data object Water : PokemonTypeModel(
        identifier = WATER,
        color = WaterColor,
        icon = R.drawable.ic_water,
        name = R.string.pokemon_type_water,
    )

    data object Grass : PokemonTypeModel(
        identifier = PokemonTypeEnum.GRASS,
        color = GrassColor,
        icon = R.drawable.ic_grass,
        name = R.string.pokemon_type_grass,
    )

    data object Dragon : PokemonTypeModel(
        identifier = PokemonTypeEnum.DRAGON,
        color = DragonColor,
        icon = R.drawable.ic_dragon,
        name = R.string.pokemon_type_dragon,
    )

    data object Electric : PokemonTypeModel(
        identifier = PokemonTypeEnum.ELECTRIC,
        color = ElectricColor,
        icon = R.drawable.ic_electric,
        name = R.string.pokemon_type_electric,
    )

    data object Fairy : PokemonTypeModel(
        identifier = PokemonTypeEnum.FAIRY,
        color = FairyColor,
        icon = R.drawable.ic_fairy,
        name = R.string.pokemon_type_fairy,
    )

    data object Ghost : PokemonTypeModel(
        identifier = PokemonTypeEnum.GHOST,
        color = GhostColor,
        icon = R.drawable.ic_ghost,
        name = R.string.pokemon_type_ghost,
    )

    data object Fire : PokemonTypeModel(
        identifier = PokemonTypeEnum.FIRE,
        color = FireColor,
        icon = R.drawable.ic_fire,
        name = R.string.pokemon_type_fire,
    )

    data object Ice : PokemonTypeModel(
        identifier = PokemonTypeEnum.ICE,
        color = IceColor,
        icon = R.drawable.ic_ice,
        name = R.string.pokemon_type_ice,
    )

    data object Bug : PokemonTypeModel(
        identifier = PokemonTypeEnum.BUG,
        color = BugColor,
        icon = R.drawable.ic_bug,
        name = R.string.pokemon_type_bug,
    )

    data object Fighting : PokemonTypeModel(
        identifier = PokemonTypeEnum.FIGHTING,
        color = FightingColor,
        icon = R.drawable.ic_fighting,
        name = R.string.pokemon_type_fighting,
    )

    data object Normal : PokemonTypeModel(
        identifier = PokemonTypeEnum.NORMAL,
        color = NormalColor,
        icon = R.drawable.ic_normal,
        name = R.string.pokemon_type_normal,
    )

    data object Dark : PokemonTypeModel(
        identifier = PokemonTypeEnum.DARK,
        color = DarkColor,
        icon = R.drawable.ic_dark,
        name = R.string.pokemon_type_dark,
    )

    data object Steel : PokemonTypeModel(
        identifier = PokemonTypeEnum.STEEL,
        color = SteelColor,
        icon = R.drawable.ic_steel,
        name = R.string.pokemon_type_steel,
    )

    data object Rock : PokemonTypeModel(
        identifier = PokemonTypeEnum.ROCK,
        color = RockColor,
        icon = R.drawable.ic_rock,
        name = R.string.pokemon_type_rock,
    )

    data object Psychic : PokemonTypeModel(
        identifier = PokemonTypeEnum.PSYCHIC,
        color = PsychicColor,
        icon = R.drawable.ic_psychic,
        name = R.string.pokemon_type_psychic,
    )

    data object Ground : PokemonTypeModel(
        identifier = PokemonTypeEnum.GROUND,
        color = GroundColor,
        icon = R.drawable.ic_ground,
        name = R.string.pokemon_type_ground,
    )

    data object Poison : PokemonTypeModel(
        identifier = PokemonTypeEnum.POISON,
        color = PoisonColor,
        icon = R.drawable.ic_poison,
        name = R.string.pokemon_type_poison,
    )

    data object Flying : PokemonTypeModel(
        identifier = PokemonTypeEnum.FLYING,
        color = FlyingColor,
        icon = R.drawable.ic_flying,
        name = R.string.pokemon_type_flying,
    )
}


