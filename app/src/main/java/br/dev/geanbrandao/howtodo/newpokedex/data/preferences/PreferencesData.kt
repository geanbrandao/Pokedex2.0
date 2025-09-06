package br.dev.geanbrandao.howtodo.newpokedex.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

interface PreferencesData {
//    val theme: Flow<String>
//    suspend fun setTheme(theme: String)
    val updateId: Flow<Int>
    suspend fun setUpdateId(value: Int)
}

private val THEME = stringPreferencesKey("theme")
private val POKEMON_INT = intPreferencesKey("updateId")

@Single
class PreferencesDataImpl(private val context: Context) : PreferencesData {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override val updateId: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[POKEMON_INT] ?: -1
    }

    override suspend fun setUpdateId(value: Int) {
        context.dataStore.edit { preferences ->
            preferences[POKEMON_INT] = value
        }
    }

//    override val theme: Flow<String> = context.dataStore.data.map { preferences ->
//        preferences[THEME] ?: ThemeOption.defaultOption.value
//    }

//    override suspend fun setTheme(theme: String) {
//        context.dataStore.edit { preferences ->
//            preferences[THEME] = theme
//        }
//    }

//    override val isListLayout: Flow<Boolean> = context.dataStore.data.map { preferences ->
//        preferences[BILL_LAYOUT].orTrue()
//    }
//
//    override suspend fun setBillLayout(layout: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[BILL_LAYOUT] = layout
//        }
//    }

//    override val onboardingViewed: Flow<Boolean> = context.dataStore.data.map { preferences ->
//        preferences[ONBOARDING_VIEWED].orFalse()
//    }
//
//    override suspend fun setOnboardingViewed(value: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[ONBOARDING_VIEWED] = value
//        }
//    }
}