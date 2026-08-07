package com.voxchat.messenger.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voxchat.messenger.data.manager.SecureStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val secureStorage: SecureStorage
) : ViewModel() {

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    fun register(name: String, login: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading

            try {
                // Формируем JID: login@voxchat.ru
                val jid = "$login@voxchat.ru"

                // Здесь должна быть реальная регистрация через XMPP (XEP-0077) или HTTP API
                // Для примера просто сохраняем данные
                secureStorage.saveJid(jid)
                secureStorage.savePassword(password)

                // Имитация задержки сети
                kotlinx.coroutines.delay(1500)

                _registerState.value = RegisterState.Success
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error(e.message ?: "Ошибка регистрации")
            }
        }
    }
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}
