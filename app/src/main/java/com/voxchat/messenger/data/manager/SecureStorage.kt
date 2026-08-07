package com.voxchat.messenger.data.manager

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureStorage(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "voxchat_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveJid(jid: String) {
        prefs.edit().putString(KEY_JID, jid).apply()
    }

    fun getJid(): String? {
        return prefs.getString(KEY_JID, null)
    }

    fun savePassword(password: String) {
        prefs.edit().putString(KEY_PASSWORD, password).apply()
    }

    fun getPassword(): String? {
        return prefs.getString(KEY_PASSWORD, null)
    }

    fun saveApiToken(token: String) {
        prefs.edit().putString(KEY_API_TOKEN, token).apply()
    }

    fun getApiToken(): String? {
        return prefs.getString(KEY_API_TOKEN, null)
    }

    fun saveDeviceId(deviceId: String) {
        prefs.edit().putString(KEY_DEVICE_ID, deviceId).apply()
    }

    fun getDeviceId(): String? {
        return prefs.getString(KEY_DEVICE_ID, null) ?: generateDeviceId()
            .also { saveDeviceId(it) }
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    private fun generateDeviceId(): String {
        return android.os.Build.SERIAL ?: java.util.UUID.randomUUID().toString()
    }

    companion object {
        private const val KEY_JID = "jid"
        private const val KEY_PASSWORD = "password"
        private const val KEY_API_TOKEN = "api_token"
        private const val KEY_DEVICE_ID = "device_id"
    }
}
