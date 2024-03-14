package br.dev.geanbrandao.howtodo.newpokedex.di

import br.dev.geanbrandao.howtodo.newpokedex.data.source.remote.KtorClient
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan("br.dev.geanbrandao.howtodo.newpokedex")
class PokemonModule {

    @Single
    fun provideKtorHttpClient() = KtorClient.httpClient
}