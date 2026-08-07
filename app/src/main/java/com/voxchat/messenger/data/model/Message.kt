package com.voxchat.messenger.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class MessageType {
    TEXT, IMAGE, VIDEO, AUDIO, DOCUMENT, LOCATION, CONTACT, VOICE, STICKER, GIF, POLL, STORY
}

enum class MessageStatus {
    SENDING, SENT, DELIVERED, READ, FAILED
}

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey val id: String,
    val chatId: String,
    val senderJid: String,
    val receiverJid: String? = null,
    val type: MessageType = MessageType.TEXT,
    val content: String,
    val mediaUrl: String? = null,
    val mediaThumbnail: String? = null,
    val mediaSize: Long = 0L,
    val mediaDuration: Long = 0L, // for voice/video in ms
    val timestamp: Long = System.currentTimeMillis(),
    val status: MessageStatus = MessageStatus.SENDING,
    val isEdited: Boolean = false,
    val isDeleted: Boolean = false,
    val replyToMessageId: String? = null,
    val forwardedFrom: String? = null,
    val reactions: Map<String, Int> = emptyMap(), // emoji -> count
    val pollOptions: List<String>? = null,
    val pollVotes: Map<Int, List<String>>? = null, // option index -> voters
    val locationLat: Double? = null,
    val locationLng: Double? = null,
    val contactName: String? = null,
    val contactPhone: String? = null,
    val storyExpiresAt: Long? = null
)
