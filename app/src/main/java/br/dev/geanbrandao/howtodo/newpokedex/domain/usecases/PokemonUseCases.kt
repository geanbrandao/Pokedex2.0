package br.dev.geanbrandao.howtodo.newpokedex.domain.usecases

import org.koin.core.annotation.Single

@Single
data class PokemonUseCases(
    val getPokemonListUseCase: GetPokemonListUseCase,
    val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    val getEvolutionChainUseCase: GetEvolutionChainUseCase,
)
