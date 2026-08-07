package com.voxchat.messenger.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class ChatType {
    PRIVATE, GROUP, CHANNEL, BROADCAST, SECRET
}

@Entity(tableName = "chats")
data class Chat(
    @PrimaryKey val id: String, // jid for private, roomJid for groups
    val name: String,
    val type: ChatType = ChatType.PRIVATE,
    val avatarUrl: String? = null,
    val lastMessageId: String? = null,
    val lastMessageText: String? = null,
    val lastMessageTime: Long = 0L,
    val unreadCount: Int = 0,
    val isPinned: Boolean = false,
    val isMuted: Boolean = false,
    val isArchived: Boolean = false,
    val participantsCount: Int = 1,
    val isAdmin: Boolean = false,
    val isOwner: Boolean = false,
    val folderId: Int? = null,
    val draftMessage: String? = null,
    val typingUsers: List<String> = emptyList(),
    val mutedUntil: Long? = null
)
