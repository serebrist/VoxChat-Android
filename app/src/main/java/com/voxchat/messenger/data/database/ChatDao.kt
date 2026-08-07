package com.voxchat.messenger.data.database

import androidx.room.*
import com.voxchat.messenger.data.model.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats WHERE id = :id")
    suspend fun getChatById(id: String): Chat?
    
    @Query("SELECT * FROM chats WHERE id = :id")
    fun getChatByIdFlow(id: String): Flow<Chat?>
    
    @Query("SELECT * FROM chats WHERE isArchived = 0 ORDER BY isPinned DESC, lastMessageTime DESC")
    fun getAllActiveChatsFlow(): Flow<List<Chat>>
    
    @Query("SELECT * FROM chats WHERE isArchived = 1 ORDER BY lastMessageTime DESC")
    fun getArchivedChatsFlow(): Flow<List<Chat>>
    
    @Query("SELECT * FROM chats WHERE isPinned = 1 AND isArchived = 0 ORDER BY lastMessageTime DESC")
    suspend fun getPinnedChats(): List<Chat>
    
    @Query("SELECT * FROM chats WHERE unreadCount > 0 ORDER BY lastMessageTime DESC")
    suspend fun getUnreadChats(): List<Chat>
    
    @Query("SELECT * FROM chats WHERE type = :type ORDER BY lastMessageTime DESC")
    fun getChatsByTypeFlow(type: com.voxchat.messenger.data.model.ChatType): Flow<List<Chat>>
    
    @Query("SELECT * FROM chats WHERE folderId = :folderId AND isArchived = 0 ORDER BY isPinned DESC, lastMessageTime DESC")
    fun getChatsByFolderFlow(folderId: Int): Flow<List<Chat>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: Chat)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChats(chats: List<Chat>)
    
    @Update
    suspend fun updateChat(chat: Chat)
    
    @Delete
    suspend fun deleteChat(chat: Chat)
    
    @Query("UPDATE chats SET lastMessageText = :text, lastMessageTime = :time, lastMessageId = :msgId WHERE id = :chatId")
    suspend fun updateLastMessage(chatId: String, msgId: String?, text: String?, time: Long)
    
    @Query("UPDATE chats SET unreadCount = unreadCount + 1 WHERE id = :chatId")
    suspend fun incrementUnreadCount(chatId: String)
    
    @Query("UPDATE chats SET unreadCount = 0 WHERE id = :chatId")
    suspend fun markAsRead(chatId: String)
    
    @Query("UPDATE chats SET isPinned = :isPinned WHERE id = :chatId")
    suspend fun setPinned(chatId: String, isPinned: Boolean)
    
    @Query("UPDATE chats SET isMuted = :isMuted, mutedUntil = :until WHERE id = :chatId")
    suspend fun setMuted(chatId: String, isMuted: Boolean, until: Long? = null)
    
    @Query("UPDATE chats SET isArchived = :isArchived WHERE id = :chatId")
    suspend fun setArchived(chatId: String, isArchived: Boolean)
    
    @Query("DELETE FROM chats WHERE id = :id")
    suspend fun deleteChatById(id: String)
    
    @Query("DELETE FROM chats")
    suspend fun deleteAllChats()
}
