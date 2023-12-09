package com.igorj.core.data.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class AuthSharedPreferences @Inject constructor(
    @ActivityContext private val context: Context
) {
    private val AUTH_SHARED_PREFERENCES = "auth_shared_preferences"

    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        context,
        AUTH_SHARED_PREFERENCES,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun empty() {
        encryptedSharedPreferences.edit().clear().apply()
    }

    fun putString(key: String, value: String) {
        encryptedSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return encryptedSharedPreferences.getString(key, null)
    }
}