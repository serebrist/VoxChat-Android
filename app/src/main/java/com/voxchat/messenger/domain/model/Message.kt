package com.voxchat.messenger.domain.model

enum class MessageType {
    TEXT,
    IMAGE,
    VIDEO,
    AUDIO,
    VOICE,
    FILE,
    LOCATION,
    CONTACT,
    POLL,
    STICKER
}

data class Message(
    val id: String,
    val chatId: String,
    val senderJid: String,
    val receiverJid: String,
    val type: MessageType,
    val content: String,
    val timestamp: Long,
    val isRead: Boolean = false,
    val isDelivered: Boolean = false,
    val isEdited: Boolean = false,
    val replyToMessageId: String? = null,
    val mediaUrl: String? = null,
    val mediaThumbnail: String? = null,
    val fileSize: Long? = null,
    val fileName: String? = null,
    val duration: Long? = null, // для аудио/видео/голосовых в мс
    val reactions: Map<String, Int> = emptyMap(), // эмодзи -> количество
    val pollOptions: List<PollOption>? = null,
    val location: LocationData? = null,
    val contact: ContactData? = null
)

data class PollOption(
    val text: String,
    val votes: Int = 0,
    val isSelected: Boolean = false
)

data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val address: String? = null
)

data class ContactData(
    val name: String,
    val phone: String? = null,
    val jid: String? = null
)
