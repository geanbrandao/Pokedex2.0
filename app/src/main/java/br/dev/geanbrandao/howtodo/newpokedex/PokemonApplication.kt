package br.dev.geanbrandao.howtodo.newpokedex

import android.app.Application
import br.dev.geanbrandao.howtodo.newpokedex.di.PokemonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class PokemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PokemonApplication)
            modules(
                PokemonModule().module
            )
        }
    }
}