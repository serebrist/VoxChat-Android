package com.voxchat.messenger.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val jid: String,
    val username: String,
    val displayName: String?,
    val avatarUrl: String?,
    val phone: String?,
    val bio: String?,
    val isOnline: Boolean = false,
    val lastSeen: Long? = null
)
