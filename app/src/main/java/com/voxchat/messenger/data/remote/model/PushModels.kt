package com.voxchat.messenger.data.remote.model

data class PushRegisterRequest(
    val jid: String,
    val token: String,
    val platform: String = "android",
    val device_id: String
)

data class PushOnlineRequest(
    val jid: String,
    val online: Boolean
)
