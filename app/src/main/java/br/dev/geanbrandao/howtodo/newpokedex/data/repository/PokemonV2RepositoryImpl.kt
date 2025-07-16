package br.dev.geanbrandao.howtodo.newpokedex.data.repository

import br.dev.geanbrandao.howtodo.newpokedex.common.decimetresToMeters
import br.dev.geanbrandao.howtodo.newpokedex.common.getDigits
import br.dev.geanbrandao.howtodo.newpokedex.common.getTypeModel
import br.dev.geanbrandao.howtodo.newpokedex.data.SerializationJsonProvider
import br.dev.geanbrandao.howtodo.newpokedex.data.local.dao.PokemonDao
import br.dev.geanbrandao.howtodo.newpokedex.data.local.entity.PokemonDetailsEntity
import br.dev.geanbrandao.howtodo.newpokedex.data.local.entity.PokemonEntity
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonV2DetailsResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonV2EvolutionChainResponse
import br.dev.geanbrandao.howtodo.newpokedex.data.remote.models.PokemonV2Response
import br.dev.geanbrandao.howtodo.newpokedex.domain.repository.PokemonV2Repository
import br.dev.geanbrandao.howtodo.newpokedex.presentation.home.HomeUiState.Companion.PAGE_SIZE
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2
import br.dev.geanbrandao.howtodo.newpokedex.presentation.models.PokemonV2Details
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Single

private const val BASE_URL: String = "https://pokeapi.co/api/v2"
private const val POKE_LIST: String = "$BASE_URL/pokemon/?offset=%d&limit=50"
private const val POKE_SPECIE: String = "$BASE_URL/pokemon-species/"
private const val POKE_INFO: String = "$BASE_URL/pokemon/"

@Single
class PokemonV2RepositoryImpl(
    private val client: HttpClient,
    private val dao: PokemonDao,
    private val json: SerializationJsonProvider,
) : PokemonV2Repository {

    override suspend fun getPokemonPage(currentPage: Int): Flow<PokemonV2> = flow {
        val from = (currentPage - 1) * PAGE_SIZE
        val to = (from + PAGE_SIZE)
        (from + 1..to).forEach { id: Int ->
            // This will now use the updated getPokemonById with staleness check
            emit(getPokemonById(id))
        }
    }

    /*
    //    private fun isDataStale(timestampString: String, days: Int): Boolean {
    //        return try {
    //            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    //            val date = dateFormat.parse(timestampString)
    //            if (date != null) {
    //                val calendar = Calendar.getInstance()
    //                calendar.add(Calendar.DAY_OF_YEAR, -days) // Subtract 'days' from current date
    //                val thresholdDate = calendar.time
    //                date.before(thresholdDate) // True if 'date' is before the 'thresholdDate'
    //            } else {
    //                true // If parsing fails, consider it stale to force a refresh
    //            }
    //        } catch (e: Exception) {
    //            // Log parsing error, don't crash the app
    //            // Consider logging e.g. Timber.e(e, "Failed to parse timestamp: $timestampString")
    //            e.printStackTrace()
    //            true // On parsing error, consider it stale
    //        }
    //    }
    */

    private suspend fun fetchAndCachePokemon(id: Int): PokemonV2 {
        val response = client.get { url(POKE_INFO.plus(id)) }.body<PokemonV2Response>()
        val result = response.toPokemonEntity()
        // save the pokemon
        dao.insertPokemon(result)
        return result.toPokemonV2()
    }

    override suspend fun getPokemonById(id: Int): PokemonV2 =
        dao.getPokemonById(id)?.toPokemonV2() ?: fetchAndCachePokemon(id)

    override suspend fun getPokemonByName(name: String): Flow<PokemonV2> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonDetailsById(id: Int): Flow<PokemonV2Details> = flowOf(
        dao.getPokemonDetailsById(id)?.let {
            toPokemonV2Details(it)
        } ?: fetchAndCachePokemonDetails(id)
    )

    override suspend fun getEvolutions(list: List<Int>): Flow<PokemonV2> = flow {
        list.forEach {
            emit(getPokemonById(it))
        }
    }

    private suspend fun fetchAndCachePokemonDetails(id: Int): PokemonV2Details {
        val responseDetails: PokemonV2DetailsResponse = client.get {
            url(POKE_SPECIE.plus(id))
        }.body<PokemonV2DetailsResponse>()

        val evolutionChain: PokemonV2EvolutionChainResponse = client.get {
            url(responseDetails.evolutionChain.url)
        }.body<PokemonV2EvolutionChainResponse>()

        val evolutions: List<Pair<String, Int>> = evolvesTo(evolutionChain.chain)

        val entity = PokemonDetailsEntity(
            id = id,
            genderRate = responseDetails.genderRate,
            evolutions = json.toJson(evolutions.map { it.second }),
            createdAt = dao.getCurrentTimestampString(),
            updatedAt = dao.getCurrentTimestampString(),
        )

        dao.insertPokemonDetails(details = entity)

        return toPokemonV2Details(entity)
    }

    private suspend fun toPokemonV2Details(
        entity: PokemonDetailsEntity,
    ): PokemonV2Details {
        val evolutions: List<Int> = json.fromJson<List<Int>>(entity.evolutions)
        val pokemon = getPokemonById(entity.id)
        return PokemonV2Details(
            pokemon = pokemon,
            genderRate = entity.genderRate,
            evolutions = evolutions,
        )
    }

    private fun evolvesTo(chain: PokemonV2EvolutionChainResponse.Chain): List<Pair<String, Int>> {
        val name = chain.species.name
        val id = chain.species.url.substringAfter("pokemon-species/").getDigits().toInt()

        val list = if (chain.evolvesTo.isNotEmpty()) {
            evolvesTo(chain.evolvesTo.first())
        } else {
            emptyList()
        }
        return listOf(Pair(name, id)) + list
    }

    private fun PokemonV2Response.toPokemonEntity() = PokemonEntity(
        id = id,
        name = name,
        typeOne = types.first().type.name,
        typeTwo = types.getOrNull(1)?.type?.name,
        height = height,
        weight = weight,
        stats = json.toJson(stats),
        abilities = json.toJson(abilities),
        createdAt = dao.getCurrentTimestampString(),
        updatedAt = dao.getCurrentTimestampString(),
    )

    private fun PokemonEntity.toPokemonV2() = PokemonV2(
        id = id,
        name = name,
        typeOne = typeOne.getTypeModel(),
        typeTwo = typeTwo?.getTypeModel(),
        height = height.decimetresToMeters(),
        weight = weight.decimetresToMeters(),
        stats = json.fromJson<List<PokemonV2Response.Stat>>(stats).map {
            PokemonV2.Stat(name = it.stat.name, value = it.baseStat)
        },
        abilities = json.fromJson<List<PokemonV2Response.Ability>>(abilities).map {
            it.ability.name
        },
        isFavorite = isFavorite,
    )
}