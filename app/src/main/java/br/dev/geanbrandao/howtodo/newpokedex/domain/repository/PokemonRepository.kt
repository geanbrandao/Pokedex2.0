package br.dev.geanbrandao.howtodo.newpokedex.domain.repository

import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonEvolutionResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonListResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonSpecieResponse

interface PokemonRepository {
    suspend fun getPokemonList(currentPage: Int) : PokemonListResponse
    suspend fun getPokemon(url: String): PokemonResponse
    suspend fun getPokemon(id: Int): PokemonResponse
    suspend fun getPokemonSpecie(id: Int): PokemonSpecieResponse
    suspend fun getPokemonEvolutionChain(url: String): PokemonEvolutionResponse
}