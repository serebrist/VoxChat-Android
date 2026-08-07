package com.voxchat.messenger.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val jid: String,
    val displayName: String,
    val avatarUrl: String?,
    val status: String,
    val isOnline: Boolean,
    val lastSeen: Long,
    val phone: String?,
    val bio: String?
)
