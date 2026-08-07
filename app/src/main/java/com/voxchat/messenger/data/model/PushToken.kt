package com.voxchat.messenger.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "push_tokens")
data class PushToken(
    @PrimaryKey val id: String = java.util.UUID.randomUUID().toString(),
    val jid: String,
    val token: String,
    val platform: String = "android",
    val deviceId: String,
    val createdAt: Long = System.currentTimeMillis(),
    val lastUsed: Long = System.currentTimeMillis()
)
