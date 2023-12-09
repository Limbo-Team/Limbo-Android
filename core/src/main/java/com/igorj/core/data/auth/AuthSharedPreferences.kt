package com.igorj.core.data.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

class AuthSharedPreferences @Inject constructor(
    private val context: Context
) {
    companion object {
        const val AUTH_SHARED_PREFERENCES = "auth_shared_preferences"
        const val USERNAME_KEY = "username_key"
        const val PASSWORD_KEY = "password_key"
        const val TOKEN_KEY = "token_key"
    }

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