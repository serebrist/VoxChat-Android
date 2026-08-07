package com.voxchat.messenger.ui.main.chatlist

import android.content.Context
import com.voxchat.messenger.data.local.dao.ChatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ChatListViewModelFactory {

    @Provides
    @ViewModelScoped
    fun provideChatListViewModel(@ApplicationContext context: Context, chatDao: ChatDao): ChatListViewModel {
        return ChatListViewModel(chatDao)
    }
}
