package br.dev.geanbrandao.howtodo.newpokedex.domain.usecases

import br.dev.geanbrandao.howtodo.newpokedex.common.decimetresToMeters
import br.dev.geanbrandao.howtodo.newpokedex.common.getTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.common.toFormattedString
import br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.models.PokemonResponse
import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonRepository
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class GetPokemonListUseCase(
    private val repository: PokemonRepository,
) {

    suspend operator fun invoke(currentPage: Int) = flow<PokemonModel> {
        val pokemonResponse = repository.getPokemonList(currentPage)

        pokemonResponse.list.map {
            val poke = repository.getPokemon(it.url).convertToModel()
            emit(poke)
        }
    }

    private fun PokemonResponse.convertToModel(): PokemonModel {
        val types = this.types.sortedBy { it.slot }
        val typeOne = types.first().type.name.getTypeModel()
        val typeTwo = types.getOrNull(1)?.type?.name?.getTypeModel()
        val abilities = this.abilities.sortedBy { it.slot }.map { it.ability.name }
        val stats = this.stats.map {
            PokemonModel.Stat(
                name = it.stat.name,
                value = it.baseStat,
            )
        }
        return PokemonModel(
            id = this.id,
            name = this.name,
            numberName = this.id.toFormattedString(),
            typeOne = typeOne!!,
            typeTwo = typeTwo,
            height = this.height.decimetresToMeters(),
            weight = this.weight.decimetresToMeters(),
            abilities = abilities,
            stats = stats,
        )
    }
}