package br.dev.geanbrandao.howtodo.newpokedex.domain.usecases

import br.dev.geanbrandao.howtodo.newpokedex.common.getDigits
import br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models.OutUrl
import br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models.PokemonEvolutionResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models.PokemonEvolutionResponse.Chain
import org.junit.Assert.assertEquals
import org.junit.Test


class GetEvolutionChainUseCaseTest {

    val model = PokemonEvolutionResponse(
        chain = Chain(
            evolvesTo = listOf(
                Chain(
                    evolvesTo = listOf(
                        Chain(
                            evolvesTo = listOf(),
                            species = OutUrl(
                                "name 3",
                                "https://pokeapi.co/api/v2/pokemon-species/3/"
                            )
                        )
                    ),
                    species = OutUrl(
                        "name 2",
                        "https://pokeapi.co/api/v2/pokemon-species/2/"
                    )
                )
            ),
            species = OutUrl(
                "name 1",
                "https://pokeapi.co/api/v2/pokemon-species/1/"
            )
        )
    )

    val expectedResult = listOf(
        "name 1" to 1,
        "name 2" to 2,
        "name 3" to 3,
    )

    @Test
    fun `test`() {
        val result = evolvesToPokemon(model.chain)
        assertEquals(expectedResult, result)
    }

    fun evolvesToPokemon(chain: Chain): List<Pair<String, Int>> {
        val pair: Pair<String, Int> = getPokeInfo(chain.species)

        val list = if (chain.evolvesTo.isNotEmpty()) {
            evolvesToPokemon(chain.evolvesTo.first())
        } else {
            emptyList()
        }

        return listOf(pair) + list
    }

    fun getPokeInfo(species: OutUrl) : Pair<String, Int> {
        val name: String = species.name
        val id: Int = species.url.substringAfter("pokemon-species/").getDigits().toInt()

        return Pair(name, id)
    }
}