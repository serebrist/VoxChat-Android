package com.voxchat.messenger.ui.main.chatdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.voxchat.messenger.databinding.ActivityChatDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatDetailBinding
    private var chatId: String = ""
    private var chatName: String = ""
    private var isGroup: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем данные из интента
        chatId = intent.getStringExtra("CHAT_ID") ?: ""
        chatName = intent.getStringExtra("CHAT_NAME") ?: "Чат"
        isGroup = intent.getBooleanExtra("IS_GROUP", false)

        setupToolbar()
        setupMessageInput()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = chatName
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupMessageInput() {
        binding.btnSend.setOnClickListener {
            val messageText = binding.etMessage.text.toString().trim()
            if (messageText.isNotEmpty()) {
                sendMessage(messageText)
                binding.etMessage.text?.clear()
            }
        }
    }

    private fun sendMessage(text: String) {
        // TODO: Реализовать отправку сообщения через XMPP
        // Для примера просто добавляем в UI
    }
}
