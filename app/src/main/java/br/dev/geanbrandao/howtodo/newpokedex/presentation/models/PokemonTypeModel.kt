package br.dev.geanbrandao.howtodo.newpokedex.presentation.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.toColorLong
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
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.UnknownColor
import br.dev.geanbrandao.howtodo.newpokedex.ui.theme.WaterColor
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
sealed class PokemonTypeModel(
    val enum: PokemonTypeEnum,
    val color: Long,
    @DrawableRes val icon: Int,
    @StringRes val name: Int,
) : Parcelable {

    data object Water : PokemonTypeModel(
        enum = WATER,
        color = WaterColor.toColorLong(),
        icon = R.drawable.ic_water,
        name = R.string.pokemon_type_water,
    )

    data object Grass : PokemonTypeModel(
        enum = PokemonTypeEnum.GRASS,
        color = GrassColor.toColorLong(),
        icon = R.drawable.ic_grass,
        name = R.string.pokemon_type_grass,
    )

    data object Dragon : PokemonTypeModel(
        enum = PokemonTypeEnum.DRAGON,
        color = DragonColor.toColorLong(),
        icon = R.drawable.ic_dragon,
        name = R.string.pokemon_type_dragon,
    )

    data object Electric : PokemonTypeModel(
        enum = PokemonTypeEnum.ELECTRIC,
        color = ElectricColor.toColorLong(),
        icon = R.drawable.ic_electric,
        name = R.string.pokemon_type_electric,
    )

    data object Fairy : PokemonTypeModel(
        enum = PokemonTypeEnum.FAIRY,
        color = FairyColor.toColorLong(),
        icon = R.drawable.ic_fairy,
        name = R.string.pokemon_type_fairy,
    )

    data object Ghost : PokemonTypeModel(
        enum = PokemonTypeEnum.GHOST,
        color = GhostColor.toColorLong(),
        icon = R.drawable.ic_ghost,
        name = R.string.pokemon_type_ghost,
    )

    data object Fire : PokemonTypeModel(
        enum = PokemonTypeEnum.FIRE,
        color = FireColor.toColorLong(),
        icon = R.drawable.ic_fire,
        name = R.string.pokemon_type_fire,
    )

    data object Ice : PokemonTypeModel(
        enum = PokemonTypeEnum.ICE,
        color = IceColor.toColorLong(),
        icon = R.drawable.ic_ice,
        name = R.string.pokemon_type_ice,
    )

    data object Bug : PokemonTypeModel(
        enum = PokemonTypeEnum.BUG,
        color = BugColor.toColorLong(),
        icon = R.drawable.ic_bug,
        name = R.string.pokemon_type_bug,
    )

    data object Fighting : PokemonTypeModel(
        enum = PokemonTypeEnum.FIGHTING,
        color = FightingColor.toColorLong(),
        icon = R.drawable.ic_fighting,
        name = R.string.pokemon_type_fighting,
    )

    data object Normal : PokemonTypeModel(
        enum = PokemonTypeEnum.NORMAL,
        color = NormalColor.toColorLong(),
        icon = R.drawable.ic_normal,
        name = R.string.pokemon_type_normal,
    )

    data object Dark : PokemonTypeModel(
        enum = PokemonTypeEnum.DARK,
        color = DarkColor.toColorLong(),
        icon = R.drawable.ic_dark,
        name = R.string.pokemon_type_dark,
    )

    data object Steel : PokemonTypeModel(
        enum = PokemonTypeEnum.STEEL,
        color = SteelColor.toColorLong(),
        icon = R.drawable.ic_steel,
        name = R.string.pokemon_type_steel,
    )

    data object Rock : PokemonTypeModel(
        enum = PokemonTypeEnum.ROCK,
        color = RockColor.toColorLong(),
        icon = R.drawable.ic_rock,
        name = R.string.pokemon_type_rock,
    )

    data object Psychic : PokemonTypeModel(
        enum = PokemonTypeEnum.PSYCHIC,
        color = PsychicColor.toColorLong(),
        icon = R.drawable.ic_psychic,
        name = R.string.pokemon_type_psychic,
    )

    data object Ground : PokemonTypeModel(
        enum = PokemonTypeEnum.GROUND,
        color = GroundColor.toColorLong(),
        icon = R.drawable.ic_ground,
        name = R.string.pokemon_type_ground,
    )

    data object Poison : PokemonTypeModel(
        enum = PokemonTypeEnum.POISON,
        color = PoisonColor.toColorLong(),
        icon = R.drawable.ic_poison,
        name = R.string.pokemon_type_poison,
    )

    data object Flying : PokemonTypeModel(
        enum = PokemonTypeEnum.FLYING,
        color = FlyingColor.toColorLong(),
        icon = R.drawable.ic_flying,
        name = R.string.pokemon_type_flying,
    )

    data object Unknown : PokemonTypeModel(
        enum = PokemonTypeEnum.UNKNOWN,
        color = UnknownColor.toColorLong(),
        icon = R.drawable.ic_pokeball,
        name = R.string.pokemon_type_unknown,
    )
}


