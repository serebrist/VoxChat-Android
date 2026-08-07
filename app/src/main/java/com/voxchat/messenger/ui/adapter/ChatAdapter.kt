package com.voxchat.messenger.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voxchat.messenger.R
import com.voxchat.messenger.domain.model.Chat
import com.voxchat.messenger.util.TimeFormatter

class ChatAdapter(
    private val onChatClick: (Chat) -> Unit,
    private val onChatLongClick: (Chat) -> Unit
) : ListAdapter<Chat, ChatAdapter.ChatViewHolder>(ChatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarView: ImageView = itemView.findViewById(R.id.avatarView)
        private val nameView: TextView = itemView.findViewById(R.id.nameView)
        private val lastMessageView: TextView = itemView.findViewById(R.id.lastMessageView)
        private val timeView: TextView = itemView.findViewById(R.id.timeView)
        private val unreadCountView: TextView = itemView.findViewById(R.id.unreadCountView)
        private val pinnedView: ImageView = itemView.findViewById(R.id.pinnedView)

        fun bind(chat: Chat) {
            nameView.text = chat.name
            lastMessageView.text = chat.lastMessage
            timeView.text = TimeFormatter.formatMessageTime(chat.lastMessageTime)

            // Отображение непрочитанных
            if (chat.unreadCount > 0) {
                unreadCountView.visibility = View.VISIBLE
                unreadCountView.text = chat.unreadCount.toString()
            } else {
                unreadCountView.visibility = View.GONE
            }

            // Закрепленные чаты
            pinnedView.visibility = if (chat.isPinned) View.VISIBLE else View.GONE

            // Индикатор онлайн
            val onlineIndicator: View = itemView.findViewById(R.id.onlineIndicator)
            onlineIndicator.visibility = if (chat.isOnline && !chat.isGroup) View.VISIBLE else View.GONE

            // Клик для открытия чата
            itemView.setOnClickListener { onChatClick(chat) }
            itemView.setOnLongClickListener { 
                onChatLongClick(chat)
                true
            }
        }
    }

    class ChatDiffCallback : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }
    }
}
