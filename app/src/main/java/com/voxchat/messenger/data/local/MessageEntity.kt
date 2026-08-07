package com.voxchat.messenger.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: String,
    val chatId: String,
    val senderJid: String,
    val text: String?,
    val timestamp: Long,
    val isOutgoing: Boolean,
    val isRead: Boolean = false,
    val isEdited: Boolean = false,
    val isDeleted: Boolean = false,
    val replyToMessageId: String? = null,
    val mediaUrl: String? = null,
    val mediaType: String? = null, // image, video, audio, file
    val reaction: String? = null
)
