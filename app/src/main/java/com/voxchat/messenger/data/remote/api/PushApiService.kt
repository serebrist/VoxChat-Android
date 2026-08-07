package com.voxchat.messenger.data.remote.api

import com.voxchat.messenger.data.remote.model.PushRegisterRequest
import com.voxchat.messenger.data.remote.model.PushOnlineRequest
import retrofit2.Response
import retrofit2.http.*

interface PushApiService {
    @POST("push/register")
    suspend fun registerPush(
        @Header("X-API-Key") apiKey: String,
        @Body request: PushRegisterRequest
    ): Response<Unit>

    @POST("push/unregister")
    suspend fun unregisterPush(
        @Header("X-API-Key") apiKey: String,
        @Body request: Map<String, String>
    ): Response<Unit>

    @POST("push/online")
    suspend fun setOnlineStatus(
        @Header("X-API-Key") apiKey: String,
        @Body request: PushOnlineRequest
    ): Response<Unit>

    @GET("push/offline/{jid}")
    suspend fun getOfflineMessages(
        @Path("jid") jid: String,
        @Query("api_key") apiKey: String
    ): Response<List<Map<String, Any>>>

    @GET("push/presence/{jid}")
    suspend fun getPresence(
        @Path("jid") jid: String,
        @Query("api_key") apiKey: String
    ): Response<Map<String, Any>>

    @GET("push/users")
    suspend fun getUsers(
        @Header("X-API-Key") apiKey: String
    ): Response<List<Map<String, Any>>>

    @GET("push/health")
    suspend fun healthCheck(): Response<Map<String, Any>>

    @POST("push/upload_url")
    suspend fun getUploadUrl(
        @Header("X-API-Key") apiKey: String,
        @Body request: Map<String, String>
    ): Response<Map<String, String>>
}
