package com.voxchat.messenger.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE jid = :jid")
    suspend fun getUserByJid(jid: String): UserEntity?
    
    @Query("SELECT * FROM users ORDER BY displayName ASC")
    fun getAllUsers(): Flow<List<UserEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Query("UPDATE users SET isOnline = :isOnline, lastSeen = :lastSeen WHERE jid = :jid")
    suspend fun updatePresence(jid: String, isOnline: Boolean, lastSeen: Long)
    
    @Delete
    suspend fun deleteUser(user: UserEntity)
}
