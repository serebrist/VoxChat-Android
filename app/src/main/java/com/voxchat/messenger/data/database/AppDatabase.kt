package com.voxchat.messenger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.voxchat.messenger.data.model.*

@Database(
    entities = [
        User::class,
        Chat::class,
        Message::class,
        PushToken::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao
    abstract fun pushTokenDao(): PushTokenDao
    
    companion object {
        const val DATABASE_NAME = "voxchat_db"
    }
}
