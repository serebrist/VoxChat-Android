package com.voxchat.messenger.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voxchat.messenger.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState
    
    fun login(jid: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            try {
                val success = authRepository.login(jid, password)
                if (success) {
                    authRepository.saveSession(jid, "session_token")
                    _loginState.value = LoginState.Success(jid)
                } else {
                    _loginState.value = LoginState.Error("Неверный логин или пароль")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "Ошибка входа")
            }
        }
    }
    
    fun navigateToRegister() {
        _loginState.value = LoginState.NavigateToRegister
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val jid: String) : LoginState()
    data class Error(val message: String) : LoginState()
    object NavigateToRegister : LoginState()
}
