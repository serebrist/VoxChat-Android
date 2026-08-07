package com.voxchat.messenger.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val jid: String,
    val username: String,
    val displayName: String,
    val avatarUrl: String? = null,
    val status: String = "",
    val isOnline: Boolean = false,
    val lastSeen: Long = 0L,
    val phone: String? = null,
    val bio: String? = null
)
