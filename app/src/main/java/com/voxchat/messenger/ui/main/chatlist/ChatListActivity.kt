package com.voxchat.messenger.ui.main.chatlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.voxchat.messenger.R
import com.voxchat.messenger.databinding.ActivityChatListBinding
import com.voxchat.messenger.ui.adapter.ChatAdapter
import com.voxchat.messenger.ui.main.chatdetail.ChatDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatListBinding

    @Inject
    lateinit var chatListViewModelFactory: ChatListViewModelFactory
    private lateinit var viewModel: ChatListViewModel
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, chatListViewModelFactory)[ChatListViewModel::class.java]

        setupToolbar()
        setupRecyclerView()
        setupFab()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "VoxChat"
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(
            onChatClick = { chat ->
                val intent = Intent(this, ChatDetailActivity::class.java).apply {
                    putExtra("CHAT_ID", chat.id)
                    putExtra("CHAT_NAME", chat.name)
                    putExtra("IS_GROUP", chat.isGroup)
                }
                startActivity(intent)
            },
            onChatLongClick = { chat ->
                // Показываем контекстное меню (архивировать, закрепить, удалить и т.д.)
                showChatOptions(chat)
            }
        )

        binding.recyclerViewChats.apply {
            layoutManager = LinearLayoutManager(this@ChatListActivity)
            adapter = chatAdapter
        }
    }

    private fun setupFab() {
        binding.fabNewChat.setOnClickListener {
            // Открыть диалог создания нового чата/группы
            showNewChatDialog()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.chats.collect { chats ->
                chatAdapter.submitList(chats)
                binding.emptyView.visibility = if (chats.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun showChatOptions(chat: com.voxchat.messenger.domain.model.Chat) {
        // Реализация контекстного меню для чата
        val options = arrayOf("Закрепить", "Без звука", "Архивировать", "Удалить")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle(chat.name)
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> viewModel.togglePin(chat.id)
                1 -> viewModel.toggleMute(chat.id)
                2 -> viewModel.archiveChat(chat.id)
                3 -> viewModel.deleteChat(chat.id)
            }
        }
        builder.show()
    }

    private fun showNewChatDialog() {
        // Диалог выбора контакта или создания группы
        val options = arrayOf("Новый чат", "Создать группу", "Создать канал")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Создать")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> showContactPicker()
                1 -> startCreateGroup(isChannel = false)
                2 -> startCreateGroup(isChannel = true)
            }
        }
        builder.show()
    }

    private fun showContactPicker() {
        // TODO: Открыть экран выбора контактов
    }

    private fun startCreateGroup(isChannel: Boolean) {
        // TODO: Открыть экран создания группы/канала
    }
}
