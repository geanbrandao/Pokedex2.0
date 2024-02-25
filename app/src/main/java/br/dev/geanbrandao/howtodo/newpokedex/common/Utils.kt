package br.dev.geanbrandao.howtodo.newpokedex.common

import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonTypeModel


object Utils {

    private const val BASE_IMG_POKE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    private const val SHINY = "$BASE_IMG_POKE_URL/shiny/"
    private const val OFFICIAL_ARTWORK_NORMAL = "$BASE_IMG_POKE_URL/other/official-artwork/"
    private const val OFFICIAL_ARTWORK_SHINY = "$BASE_IMG_POKE_URL/other/official-artwork/shiny/"
    private const val IMG_EXT = ".png"

    fun getImgOfficialNormal(index: Int) = buildString {
        append(OFFICIAL_ARTWORK_NORMAL)
        append(index)
        append(IMG_EXT)
    }
    fun getImgOfficialShiny(index: Int) = buildString {
        append(OFFICIAL_ARTWORK_SHINY)
        append(index)
        append(IMG_EXT)
    }

    fun getFallbackImageNormal(index: Int) = buildString {
        append(BASE_IMG_POKE_URL)
        append(index)
        append(IMG_EXT)
    }

    fun getFallbackImageShiny(index: Int) = buildString {
        append(SHINY)
        append(index)
        append(IMG_EXT)
    }

    private fun getPokemonWeakness(
        typeOne: PokemonTypeModel,
        typeTwo: PokemonTypeModel?
    ) : List<PokemonTypeModel> {
        val listOne = typeOne.getPokemonTypeWeaknesses()
        val listTwo = typeTwo?.getPokemonTypeWeaknesses() ?: listOf()
        return listOne.union(listTwo).toList().sortedBy { it.name }
    }
}