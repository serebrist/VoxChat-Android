package com.voxchat.messenger.domain.model

data class User(
    val id: String,
    val jid: String,
    val displayName: String,
    val avatarUrl: String? = null,
    val status: String = "",
    val isOnline: Boolean = false,
    val lastSeen: Long = 0L,
    val phone: String? = null,
    val bio: String? = null
)
