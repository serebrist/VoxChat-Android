package com.voxchat.messenger.data.database

import androidx.room.*
import com.voxchat.messenger.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE jid = :jid")
    suspend fun getUserByJid(jid: String): User?
    
    @Query("SELECT * FROM users WHERE jid = :jid")
    fun getUserByJidFlow(jid: String): Flow<User?>
    
    @Query("SELECT * FROM users ORDER BY displayName ASC")
    suspend fun getAllUsers(): List<User>
    
    @Query("SELECT * FROM users ORDER BY displayName ASC")
    fun getAllUsersFlow(): Flow<List<User>>
    
    @Query("SELECT * FROM users WHERE isOnline = 1")
    suspend fun getOnlineUsers(): List<User>
    
    @Query("SELECT * FROM users WHERE isOnline = 1")
    fun getOnlineUsersFlow(): Flow<List<User>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)
    
    @Update
    suspend fun updateUser(user: User)
    
    @Delete
    suspend fun deleteUser(user: User)
    
    @Query("UPDATE users SET isOnline = :isOnline, lastSeen = :lastSeen WHERE jid = :jid")
    suspend fun updateOnlineStatus(jid: String, isOnline: Boolean, lastSeen: Long = System.currentTimeMillis())
    
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}
