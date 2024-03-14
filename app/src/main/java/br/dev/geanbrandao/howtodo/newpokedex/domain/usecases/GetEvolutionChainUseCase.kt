package br.dev.geanbrandao.howtodo.newpokedex.domain.usecases

import br.dev.geanbrandao.howtodo.newpokedex.common.getDigits
import br.dev.geanbrandao.howtodo.newpokedex.common.getTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.common.toFormattedString
import br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models.OutUrl
import br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models.PokemonEvolutionResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models.PokemonResponse
import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonRepository
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonEvolutionModel
import org.koin.core.annotation.Factory

@Factory
class GetEvolutionChainUseCase(
    private val repository: PokemonRepository,
) {

    suspend operator fun invoke(url: String) : List<PokemonEvolutionModel> {
        val response = repository.getPokemonEvolutionChain(url)
        val evolutions = evolvesToPokemon(response.chain)
        val results = evolutions.map {
            repository.getPokemon(it.second).toPokemonEvolution()
        }
        return results
    }

    private fun PokemonResponse.toPokemonEvolution(): PokemonEvolutionModel {
        val types = this.types.sortedBy { it.slot }
        val typeOne = types.first().type.name.getTypeModel()
        val typeTwo = types.getOrNull(1)?.type?.name?.getTypeModel()

        return PokemonEvolutionModel(
            id = id,
            numberName = id.toFormattedString(),
            name = name,
            typeOne = typeOne!!,
            typeTwo = typeTwo,
        )
    }

    private fun evolvesToPokemon(chain: PokemonEvolutionResponse.Chain): List<Pair<String, Int>> {
        val pair: Pair<String, Int> = getPokeInfo(chain.species)

        val list = if (chain.evolvesTo.isNotEmpty()) {
            evolvesToPokemon(chain.evolvesTo.first())
        } else {
            emptyList()
        }

        return listOf(pair) + list
    }

    private fun getPokeInfo(species: OutUrl) : Pair<String, Int> {
        val name: String = species.name
        val id: Int = species.url.substringAfter("pokemon-species/").getDigits().toInt()

        return Pair(name, id)
    }


}