package br.dev.geanbrandao.howtodo.newpokedex.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import br.dev.geanbrandao.howtodo.newpokedex.data.local.AppDatabase
import br.dev.geanbrandao.howtodo.newpokedex.data.local.DB_NAME
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import java.util.concurrent.Executors


@Module
@ComponentScan("br.dev.geanbrandao.howtodo.newpokedex")
class PokemonModule {

    @Single
    fun provideJson() = Json { ignoreUnknownKeys = true }

    @Single
    fun provideKtorHttpClient(json: Json) = HttpClient(Android) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger ktor =>", message)
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.v("HTTP Status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        install(HttpCache)
    }

    @Single
    fun provideDatabase(appContext: Context) = Room.databaseBuilder(
        context = appContext,
        klass = AppDatabase::class.java,
        name = DB_NAME
    ).setQueryCallback(
        queryCallback = { query, bindArgs ->
            Log.d("DB_LOG", "Query: $query, Args: $bindArgs")
        },
        executor = Executors.newSingleThreadExecutor(),
    ).build()

    @Single
    fun providePokemonDao(database: AppDatabase) = database.pokemonDao
}