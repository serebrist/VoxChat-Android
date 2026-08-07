package com.voxchat.messenger.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: String,
    val chatId: String,
    val senderJid: String,
    val receiverJid: String,
    val type: String, // TEXT, IMAGE, VIDEO...
    val content: String,
    val timestamp: Long,
    val isRead: Boolean,
    val isDelivered: Boolean,
    val isEdited: Boolean,
    val replyToMessageId: String?,
    val mediaUrl: String?,
    val mediaThumbnail: String?,
    val fileSize: Long?,
    val fileName: String?,
    val duration: Long?,
    val reactions: String, // JSON Map
    val pollOptions: String?, // JSON List
    val locationData: String?, // JSON
    val contactData: String? // JSON
)
