package br.dev.geanbrandao.howtodo.newpokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.dev.geanbrandao.howtodo.newpokedex.data.local.entity.PokemonDetailsEntity
import br.dev.geanbrandao.howtodo.newpokedex.data.local.entity.PokemonEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Dao
interface PokemonDao {

    /**
     * Helper function to get the current timestamp in "YYYY-MM-DD HH:MM:SS" format,
     * which is compatible with SQLite's CURRENT_TIMESTAMP default format.
     */
    fun getCurrentTimestampString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }

    /**
     * Inserts a new Pokemon into the table.
     * If you construct a PokemonEntity without specifying createdAt or updatedAt,
     * SQLite's DEFAULT "CURRENT_TIMESTAMP" will be used.
     * If you want to control the timestamp from Kotlin, set them explicitly on the entity.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity)

    /**
     * Updates an existing Pokemon.
     * IMPORTANT: You MUST update the 'updatedAt' field on the PokemonEntity instance
     * passed to this method before calling it to correctly reflect the update time.
     *
     * Example Usage (in a ViewModel/Repository):
     * val pokemon = getPokemonById(1)
     * if (pokemon != null) {
     *     val updated = pokemon.copy(name = "New Name", updatedAt = dao.getCurrentTimestampString())
     *     dao.updatePokemon(updated)
     * }
     */
    @Update
    suspend fun updatePokemon(pokemon: PokemonEntity)

    /**
     * Retrieves a Pokemon by its ID.
     */
    @Query("SELECT * FROM pokemon WHERE id = :id")
    suspend fun getPokemonById(id: Int): PokemonEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonDetails(details: PokemonDetailsEntity)

    @Query("SELECT * FROM pokemon_details WHERE id = :id")
    suspend fun getPokemonDetailsById(id: Int): PokemonDetailsEntity?

    /**
     * Retrieves all Pokemons whose 'updatedAt' timestamp is older than 7 days.
     * SQLite's STRFTIME function is used for date comparison.
     * 'now' gives the current UTC timestamp.
     * '-7 days' subtracts 7 days from the current time.
     */
    @Query("SELECT * FROM pokemon WHERE updatedAt < STRFTIME('%Y-%m-%d %H:%M:%S', 'now', '-7 days')")
    suspend fun getPokemonsUpdatedOlderThanAWeek(): List<PokemonEntity>

    /**
     * Retrieves all Pokemons whose 'createdAt' timestamp is older than 7 days.
     * This is similar to the above but checks the creation date.
     */
    @Query("SELECT * FROM pokemon WHERE createdAt < STRFTIME('%Y-%m-%d %H:%M:%S', 'now', '-7 days')")
    suspend fun getPokemonsCreatedOlderThanAWeek(): List<PokemonEntity>
}
