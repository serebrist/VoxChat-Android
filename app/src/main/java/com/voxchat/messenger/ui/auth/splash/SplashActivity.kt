package com.voxchat.messenger.ui.auth.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.voxchat.messenger.data.manager.SecureStorage
import com.voxchat.messenger.ui.main.chatlist.ChatListActivity
import com.voxchat.messenger.ui.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var secureStorage: SecureStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Показываем сплеш 2 секунды
        Handler(Looper.getMainLooper()).postDelayed({
            checkAuthAndNavigate()
        }, 2000)
    }

    private fun checkAuthAndNavigate() {
        lifecycleScope.launch {
            val jid = secureStorage.getJid()
            val password = secureStorage.getPassword()

            val intent = if (!jid.isNullOrEmpty() && !password.isNullOrEmpty()) {
                // Пользователь авторизован - переходим к чатам
                Intent(this@SplashActivity, ChatListActivity::class.java)
            } else {
                // Нет сессии - переходим на экран входа
                Intent(this@SplashActivity, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
