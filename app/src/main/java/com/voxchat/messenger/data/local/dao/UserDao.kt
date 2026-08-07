package com.voxchat.messenger.data.local.dao

import androidx.room.*
import com.voxchat.messenger.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE jid = :jid")
    suspend fun getUserByJid(jid: String): UserEntity?

    @Query("SELECT * FROM users WHERE jid = :jid")
    fun getUserByJidFlow(jid: String): Flow<UserEntity?>

    @Query("SELECT * FROM users ORDER BY displayName ASC")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM users WHERE displayName LIKE :query OR jid LIKE :query")
    fun searchUsers(query: String): Flow<List<UserEntity>>
}
