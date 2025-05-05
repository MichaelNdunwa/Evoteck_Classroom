package com.evoteckgeospatialconsult.core.datastore

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SecurePreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPrefs = context.getSharedPreferences("secure_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPrefs.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        return sharedPrefs.getString("auth_token", null)
    }

    fun clearSession() {
        sharedPrefs.edit().clear().apply()
    }

    fun saveUserId(userId: String?) {
        sharedPrefs.edit().putString("user_id", userId).apply()
    }

    fun getUserId(): String? {
        return sharedPrefs.getString("user_id", null)
    }

    fun clearUserId() {
        sharedPrefs.edit().remove("user_id").apply()
    }


}