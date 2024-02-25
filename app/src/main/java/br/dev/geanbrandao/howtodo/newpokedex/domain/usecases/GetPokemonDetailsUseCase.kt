package br.dev.geanbrandao.howtodo.newpokedex.domain.usecases

import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonRepository
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonDetailsModel
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonModel
import org.koin.core.annotation.Factory

@Factory
class GetPokemonDetailsUseCase(
    private val repository: PokemonRepository,
) {
    suspend operator fun invoke(pokemon: PokemonModel): PokemonDetailsModel {
        val pokemonSpecie = repository.getPokemonSpecie(pokemon.id)
//        val evolutionChain = repository.getPokemonEvolutionChain(evolutionUrl)
        return PokemonDetailsModel(
            pokemon = pokemon,
            evolutionUrl = pokemonSpecie.evolutionChain.url,
            generation = pokemonSpecie.generation.name,
            genderRate = pokemonSpecie.genderRate,
        )
    }
}