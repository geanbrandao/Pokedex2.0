package br.dev.geanbrandao.howtodo.newpokedex.domain.repository

import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2Details
import kotlinx.coroutines.flow.Flow

interface PokemonV2Repository {
    suspend fun getPokemonPage(currentPage: Int): Flow<PokemonV2>
    suspend fun getPokemonById(id: Int): PokemonV2
    suspend fun getPokemonByName(name: String): Flow<PokemonV2>
    suspend fun getPokemonDetailsById(id: Int): Flow<PokemonV2Details>
    suspend fun getEvolutions(list: List<Int>): Flow<PokemonV2>
}