package com.voxchat.messenger.data.network

import com.voxchat.messenger.data.model.PushToken
import retrofit2.Response
import retrofit2.http.*

interface PushApiService {
    
    @POST("push/register")
    suspend fun registerPushToken(
        @Header("X-API-Key") apiKey: String,
        @Body request: RegisterPushRequest
    ): Response<Unit>
    
    @POST("push/unregister")
    suspend fun unregisterPushToken(
        @Header("X-API-Key") apiKey: String,
        @Body request: UnregisterPushRequest
    ): Response<Unit>
    
    @POST("push/online")
    suspend fun setOnlineStatus(
        @Header("X-API-Key") apiKey: String,
        @Body request: OnlineStatusRequest
    ): Response<Unit>
    
    @GET("push/offline/{jid}")
    suspend fun getOfflineMessages(
        @Path("jid") jid: String,
        @Query("api_key") apiKey: String
    ): Response<List<OfflineMessageResponse>>
    
    @GET("push/presence/{jid}")
    suspend fun getUserPresence(
        @Path("jid") jid: String,
        @Query("api_key") apiKey: String
    ): Response<PresenceResponse>
    
    @POST("push/upload_url")
    suspend fun getUploadUrl(
        @Header("X-API-Key") apiKey: String,
        @Body request: UploadUrlRequest
    ): Response<UploadUrlResponse>
    
    @GET("push/file/{name}")
    suspend fun downloadFile(
        @Path("name") fileName: String,
        @Query("api_key") apiKey: String
    ): Response<okhttp3.ResponseBody>
    
    @GET("push/health")
    suspend fun healthCheck(): Response<HealthResponse>
}

data class RegisterPushRequest(
    val jid: String,
    val token: String,
    val platform: String = "android",
    val device_id: String
)

data class UnregisterPushRequest(
    val jid: String,
    val device_id: String
)

data class OnlineStatusRequest(
    val jid: String,
    val online: Boolean
)

data class OfflineMessageResponse(
    val id: String,
    val from: String,
    val body: String,
    val timestamp: Long
)

data class PresenceResponse(
    val jid: String,
    val online: Boolean,
    val lastSeen: Long?
)

data class UploadUrlRequest(
    val filename: String,
    val contentType: String,
    val size: Long
)

data class UploadUrlResponse(
    val url: String,
    val fields: Map<String, String>?
)

data class HealthResponse(
    val status: String,
    val version: String
)
