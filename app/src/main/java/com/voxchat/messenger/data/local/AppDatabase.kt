package com.voxchat.messenger.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.voxchat.messenger.data.local.dao.*
import com.voxchat.messenger.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        ChatEntity::class,
        MessageEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao

    companion object {
        const val DATABASE_NAME = "voxchat_db"
    }
}
