package br.dev.geanbrandao.howtodo.newpokedex.data.repository

import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonEvolutionResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonListResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonSpecieResponse
import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonRepository
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.HomeUiState.Companion.PAGE_SIZE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import org.koin.core.annotation.Single

private const val BASE_URL: String = "https://pokeapi.co/api/v2"
private const val POKE_LIST: String = "$BASE_URL/pokemon/?offset=%d&limit=20"
private const val POKE_SPECIE: String = "$BASE_URL/pokemon-species/"
private const val POKE_INFO: String = "$BASE_URL/pokemon/"

@Single
class PokemonRepositoryImpl(
    private val client: HttpClient,
) : PokemonRepository {

    override suspend fun getPokemonList(currentPage: Int): PokemonListResponse {
        return client.get {
            url(POKE_LIST.format((currentPage - 1) * PAGE_SIZE))
        }.body()
    }

    override suspend fun getPokemon(url: String): PokemonResponse {
        return client.get { url(url) }.body()
    }

    override suspend fun getPokemon(id: Int): PokemonResponse {
        return client.get { url(POKE_INFO.plus(id)) }.body()
    }

    override suspend fun getPokemonSpecie(id: Int): PokemonSpecieResponse {
        return client.get { url(POKE_SPECIE.plus(id)) }.body()
    }

    override suspend fun getPokemonEvolutionChain(url: String): PokemonEvolutionResponse {
        return client.get { url(url)}.body()
    }
}