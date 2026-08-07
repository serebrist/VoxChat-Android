package com.voxchat.messenger.data.database

import androidx.room.*
import com.voxchat.messenger.data.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC LIMIT 50 OFFSET :offset")
    suspend fun getMessagesForChat(chatId: String, offset: Int = 0): List<Message>
    
    @Query("SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC")
    fun getMessagesForChatFlow(chatId: String): Flow<List<Message>>
    
    @Query("SELECT * FROM messages WHERE id = :id")
    suspend fun getMessageById(id: String): Message?
    
    @Query("SELECT * FROM messages WHERE chatId = :chatId AND status = 0 ORDER BY timestamp ASC LIMIT 10")
    suspend fun getPendingMessages(chatId: String): List<Message>
    
    @Query("SELECT * FROM messages WHERE senderJid = :jid ORDER BY timestamp DESC LIMIT 20")
    suspend fun getMessagesFromUser(jid: String): List<Message>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<Message>)
    
    @Update
    suspend fun updateMessage(message: Message)
    
    @Delete
    suspend fun deleteMessage(message: Message)
    
    @Query("UPDATE messages SET status = :status WHERE id = :id")
    suspend fun updateMessageStatus(id: String, status: com.voxchat.messenger.data.model.MessageStatus)
    
    @Query("UPDATE messages SET isDeleted = 1, content = '' WHERE id = :id")
    suspend fun markMessageAsDeleted(id: String)
    
    @Query("UPDATE messages SET isEdited = 1 WHERE id = :id")
    suspend fun markMessageAsEdited(id: String)
    
    @Query("DELETE FROM messages WHERE chatId = :chatId")
    suspend fun deleteAllMessagesForChat(chatId: String)
    
    @Query("DELETE FROM messages")
    suspend fun deleteAllMessages()
    
    @Query("SELECT COUNT(*) FROM messages WHERE chatId = :chatId")
    suspend fun getMessageCountForChat(chatId: String): Int
    
    @Query("SELECT * FROM messages WHERE replyToMessageId = :messageId")
    suspend fun getRepliesToMessage(messageId: String): List<Message>
}
