package com.rksrtx76.nextbuy.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesDataStore(private val context : Context) {

    companion object{
        // Context.datastore  is a variable name
        private val Context.datastore : DataStore<Preferences> by preferencesDataStore("user_preferences")
        private val IS_FIRST_TIME_LOGIN = booleanPreferencesKey("is_first_time_login")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    val isFirstTimeLogin : Flow<Boolean> = context.datastore.data.map { preferences ->
        preferences[IS_FIRST_TIME_LOGIN] ?: true
    }

    val isLoggedIn : Flow<Boolean> = context.datastore.data.map { preferences ->
        preferences[IS_LOGGED_IN] ?: false
    }

    suspend fun setFirstTimeLogin(isFirstTime : Boolean){
        context.datastore.edit { preferences ->
            preferences[IS_FIRST_TIME_LOGIN] = isFirstTime
        }
    }

    suspend fun setLoggedIn(isLoggedIn : Boolean){
        context.datastore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }
}