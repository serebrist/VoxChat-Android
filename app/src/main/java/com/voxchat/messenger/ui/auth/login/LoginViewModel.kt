package com.voxchat.messenger.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voxchat.messenger.data.manager.SecureStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val secureStorage: SecureStorage
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(jid: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                // Здесь должна быть реальная аутентификация через XMPP
                // Для примера просто сохраняем данные
                secureStorage.saveJid(jid)
                secureStorage.savePassword(password)

                // Имитация задержки сети
                kotlinx.coroutines.delay(1000)

                _loginState.value = LoginState.Success
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Ошибка входа")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}
