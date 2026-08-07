package com.voxchat.messenger.ui.main.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voxchat.messenger.data.local.dao.ChatDao
import com.voxchat.messenger.domain.model.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val chatDao: ChatDao
) : ViewModel() {

    private val _chats = MutableStateFlow<List<Chat>>(emptyList())
    val chats: StateFlow<List<Chat>> = _chats

    init {
        loadChats()
    }

    private fun loadChats() {
        viewModelScope.launch {
            // Загрузка тестовых данных
            _chats.value = getMockChats()
        }
    }

    fun togglePin(chatId: String) {
        viewModelScope.launch {
            chatDao.updatePinnedStatus(chatId, true)
        }
    }

    fun toggleMute(chatId: String) {
        viewModelScope.launch {
            chatDao.updateMutedStatus(chatId, true)
        }
    }

    fun archiveChat(chatId: String) {
        viewModelScope.launch {
            // Логика архивации
        }
    }

    fun deleteChat(chatId: String) {
        viewModelScope.launch {
            // Логика удаления
        }
    }

    private fun getMockChats(): List<Chat> {
        return listOf(
            Chat("1", "Павел Дуров", null, "Привет! Как дела?", System.currentTimeMillis(), 2, true, false, false, false, 1, true),
            Chat("2", "Работа", null, "Совещание в 15:00", System.currentTimeMillis() - 3600000, 0, false, false, true, false, 15, false),
            Chat("3", "Семья", null, "Мама: Когда приедешь?", System.currentTimeMillis() - 7200000, 1, false, false, false, false, 5, false),
            Chat("4", "Новости IT", null, "Вышел новый Android 15", System.currentTimeMillis() - 86400000, 0, false, false, false, true, 1250, false)
        )
    }
}
