package br.dev.geanbrandao.howtodo.newpokedex.common.preview

import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2Details

object PreviewHelper {

    val bulbasaur = PokemonV2(
        id = 1,
        name = "bulbasaur",
        typeOne = PokemonTypeModel.Grass,
        typeTwo = PokemonTypeModel.Poison,
        height = 0.7f,
        weight = 6.9f,
        abilities = listOf(
            "overgrow",
            "chlorophyll",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 65),
            PokemonV2.Stat("attack", 10),
            PokemonV2.Stat("defense", 50),
        ),
    )

    val bulbasaurDetails = PokemonV2Details(
        pokemon = bulbasaur,
        genderRate = 1,
        evolutions = listOf(2, 3),
    )

    val ivysaur = PokemonV2(
        id = 2,
        name = "ivysaur",
        typeOne = PokemonTypeModel.Grass,
        typeTwo = PokemonTypeModel.Poison,
        height = 0.9f,
        weight = 13.0f,
        abilities = listOf(
            "leafsurge",
            "toxicleaf",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 75),
            PokemonV2.Stat("attack", 60),
            PokemonV2.Stat("defense", 65),
        ),
    )

    val ivysaurDetails = PokemonV2Details(
        pokemon = ivysaur,
        genderRate = 1,
        evolutions = listOf(1, 2, 3),
    )

    val venusaur = PokemonV2(
        id = 3,
        name = "venusaur",
        typeOne = PokemonTypeModel.Grass,
        typeTwo = PokemonTypeModel.Poison,
        height = 1.9f,
        weight = 268.9f,
        abilities = listOf(
            "overgrow",
            "chlorophyll",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 65),
            PokemonV2.Stat("attack", 10),
            PokemonV2.Stat("defense", 50),
        ),
    )

    val venusaurDetails = PokemonV2Details(
        pokemon = venusaur,
        genderRate = 1,
        evolutions = listOf(1, 2, 3),
    )

    val charmander = PokemonV2(
        id = 4,
        name = "charmander",
        typeOne = PokemonTypeModel.Fire,
        typeTwo = null,
        height = 0.6f,
        weight = 8.5f,
        abilities = listOf(
            "flamespit",
            "embertrail",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 39),
            PokemonV2.Stat("attack", 52),
            PokemonV2.Stat("defense", 43),
        ),
    )

    val squirtle = PokemonV2(
        id = 7,
        name = "squirtle",
        typeOne = PokemonTypeModel.Water,
        typeTwo = null,
        height = 0.5f,
        weight = 9.0f,
        abilities = listOf(
            "aquaarmor",
            "bubblejet",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 44),
            PokemonV2.Stat("attack", 48),
            PokemonV2.Stat("defense", 65),
        ),
    )

    val pikachu = PokemonV2(
        id = 25,
        name = "pikachu",
        typeOne = PokemonTypeModel.Electric,
        typeTwo = null,
        height = 0.4f,
        weight = 6.0f,
        abilities = listOf(
            "voltstrike",
            "sparkstep",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 35),
            PokemonV2.Stat("attack", 55),
            PokemonV2.Stat("defense", 40),
        ),
    )

    val clefairy = PokemonV2(
        id = 35,
        name = "clefairy",
        typeOne = PokemonTypeModel.Fairy,
        typeTwo = null,
        height = 0.6f,
        weight = 7.5f,
        abilities = listOf(
            "moondust",
            "healingcharm",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 70),
            PokemonV2.Stat("attack", 45),
            PokemonV2.Stat("defense", 48),
        ),
    )

    val onix = PokemonV2(
        id = 95,
        name = "onix",
        typeOne = PokemonTypeModel.Rock,
        typeTwo = PokemonTypeModel.Ground,
        height = 8.8f,
        weight = 210.0f,
        abilities = listOf(
            "rockbind",
            "earthcoil",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 35),
            PokemonV2.Stat("attack", 45),
            PokemonV2.Stat("defense", 160),
        ),
    )

    val koffing = PokemonV2(
        id = 109,
        name = "koffing",
        typeOne = PokemonTypeModel.Poison,
        typeTwo = null,
        height = 0.6f,
        weight = 1.0f,
        abilities = listOf(
            "gascloud",
            "toxicmist",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 40),
            PokemonV2.Stat("attack", 65),
            PokemonV2.Stat("defense", 95),
        ),
    )

    val mew = PokemonV2(
        id = 151,
        name = "mew",
        typeOne = PokemonTypeModel.Psychic,
        typeTwo = null,
        height = 0.4f,
        weight = 4.0f,
        abilities = listOf(
            "genesurge",
            "psyflip",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 100),
            PokemonV2.Stat("attack", 100),
            PokemonV2.Stat("defense", 100),
        ),
    )

    val ditto = PokemonV2(
        id = 132,
        name = "ditto",
        typeOne = PokemonTypeModel.Normal,
        typeTwo = null,
        height = 0.3f,
        weight = 4.0f,
        abilities = listOf(
            "copycore",
            "formshift",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 48),
            PokemonV2.Stat("attack", 48),
            PokemonV2.Stat("defense", 48),
        ),
    )

    val suicune = PokemonV2(
        id = 245,
        name = "suicune",
        typeOne = PokemonTypeModel.Water,
        typeTwo = null,
        height = 2.0f,
        weight = 187.0f,
        abilities = listOf(
            "auraice",
            "mirageflow",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 100),
            PokemonV2.Stat("attack", 75),
            PokemonV2.Stat("defense", 115),
        ),
    )

    val rayquaza = PokemonV2(
        id = 384,
        name = "rayquaza",
        typeOne = PokemonTypeModel.Dragon,
        typeTwo = null,
        height = 7.0f,
        weight = 206.5f,
        abilities = listOf(
            "skystorm",
            "aerofury",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 105),
            PokemonV2.Stat("attack", 150),
            PokemonV2.Stat("defense", 90),
        ),
    )

    val toucannon = PokemonV2(
        id = 733,
        name = "toucannon",
        typeOne = PokemonTypeModel.Flying,
        typeTwo = PokemonTypeModel.Normal,
        height = 1.1f,
        weight = 26.0f,
        abilities = listOf(
            "sonicbeak",
            "flarecall",
        ),
        isFavorite = false,
        stats = listOf(
            PokemonV2.Stat("hp", 80),
            PokemonV2.Stat("attack", 120),
            PokemonV2.Stat("defense", 75),
        ),
    )
}