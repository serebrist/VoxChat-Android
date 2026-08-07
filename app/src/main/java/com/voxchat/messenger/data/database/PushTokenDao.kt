package com.voxchat.messenger.data.database

import androidx.room.*
import com.voxchat.messenger.data.model.PushToken
import kotlinx.coroutines.flow.Flow

@Dao
interface PushTokenDao {
    @Query("SELECT * FROM push_tokens WHERE jid = :jid")
    suspend fun getTokenForUser(jid: String): PushToken?
    
    @Query("SELECT * FROM push_tokens WHERE deviceId = :deviceId")
    suspend fun getTokenByDevice(deviceId: String): PushToken?
    
    @Query("SELECT * FROM push_tokens")
    fun getAllTokensFlow(): Flow<List<PushToken>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: PushToken)
    
    @Update
    suspend fun updateToken(token: PushToken)
    
    @Delete
    suspend fun deleteToken(token: PushToken)
    
    @Query("DELETE FROM push_tokens WHERE jid = :jid")
    suspend fun deleteTokensForUser(jid: String)
    
    @Query("DELETE FROM push_tokens")
    suspend fun deleteAllTokens()
    
    @Query("UPDATE push_tokens SET lastUsed = :timestamp WHERE id = :id")
    suspend fun updateLastUsed(id: String, timestamp: Long = System.currentTimeMillis())
}
