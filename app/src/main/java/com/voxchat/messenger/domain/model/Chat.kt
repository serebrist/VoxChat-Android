package com.voxchat.messenger.domain.model

data class Chat(
    val id: String,
    val name: String,
    val avatarUrl: String? = null,
    val lastMessage: String? = null,
    val lastMessageTime: Long = 0L,
    val unreadCount: Int = 0,
    val isPinned: Boolean = false,
    val isMuted: Boolean = false,
    val isGroup: Boolean = false,
    val isChannel: Boolean = false,
    val participantsCount: Int = 1,
    val isOnline: Boolean = false,
    val typingUsers: List<String> = emptyList(),
    val folderId: String? = null
)

data class Group(
    val id: String,
    val name: String,
    val description: String? = null,
    val avatarUrl: String? = null,
    val ownerJid: String,
    val participantsCount: Int = 0,
    val isAdmin: Boolean = false,
    val isSuperGroup: Boolean = false,
    val inviteLink: String? = null,
    val createdAt: Long = 0L
)

data class Channel(
    val id: String,
    val name: String,
    val description: String? = null,
    val avatarUrl: String? = null,
    val ownerJid: String,
    val subscribersCount: Int = 0,
    val isAdmin: Boolean = false,
    val inviteLink: String? = null,
    val createdAt: Long = 0L
)

data class CallRecord(
    val id: String,
    val contactName: String,
    val contactJid: String,
    val avatarUrl: String? = null,
    val timestamp: Long,
    val duration: Long,
    val isIncoming: Boolean,
    val isVideo: Boolean,
    val isMissed: Boolean,
    val isGroup: Boolean = false
)

data class Story(
    val id: String,
    val userId: String,
    val userName: String,
    val userAvatar: String? = null,
    val mediaUrl: String,
    val mediaType: MediaType,
    val timestamp: Long,
    val expiresAt: Long,
    val isViewed: Boolean = false
)

enum class MediaType {
    IMAGE,
    VIDEO
}

data class Folder(
    val id: String,
    val name: String,
    val icon: String,
    val chatIds: List<String> = emptyList()
)
