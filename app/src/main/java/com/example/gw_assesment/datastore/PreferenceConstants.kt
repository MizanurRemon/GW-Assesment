package com.example.gw_assesment.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceConstants {
    const val PREFERENCE_NAME = "gw_preferences"
    
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    val USER_TOKEN = stringPreferencesKey("user_token")
}
