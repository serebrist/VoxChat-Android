package com.voxchat.messenger.util

import java.text.SimpleDateFormat
import java.util.*

object TimeFormatter {
    private val todayFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val yesterdayFormat = SimpleDateFormat("'Вчера' HH:mm", Locale.getDefault())
    private val weekFormat = SimpleDateFormat("EEEE HH:mm", Locale("ru"))
    private val oldFormat = SimpleDateFormat("dd.MM.yy HH:mm", Locale.getDefault())

    fun formatMessageTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val messageDate = Date(timestamp)
        val calendar = Calendar.getInstance()
        calendar.time = messageDate

        val today = Calendar.getInstance()
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DAY_OF_YEAR, -1)

        return when {
            isSameDay(calendar, today) -> todayFormat.format(messageDate)
            isSameDay(calendar, yesterday) -> yesterdayFormat.format(messageDate)
            isThisWeek(calendar) -> weekFormat.format(messageDate)
            else -> oldFormat.format(messageDate)
        }
    }

    fun formatLastSeen(lastSeen: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - lastSeen

        return when {
            diff < 60_000 -> "только что"
            diff < 3_600_000 -> "${diff / 60_000} мин назад"
            diff < 86_400_000 -> "${diff / 3_600_000} ч назад"
            diff < 604_800_000 -> "${diff / 86_400_000} дн назад"
            else -> formatMessageTime(lastSeen)
        }
    }

    fun formatFileSize(bytes: Long): String {
        return when {
            bytes < 1024 -> "$bytes Б"
            bytes < 1_048_576 -> "${bytes / 1024} КБ"
            bytes < 1_073_741_824 -> "${bytes / 1_048_576} МБ"
            else -> "${bytes / 1_073_741_824} ГБ"
        }
    }

    private fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }

    private fun isThisWeek(calendar: Calendar): Boolean {
        val now = Calendar.getInstance()
        return calendar.get(Calendar.WEEK_OF_YEAR) == now.get(Calendar.WEEK_OF_YEAR) &&
                calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
    }
}
