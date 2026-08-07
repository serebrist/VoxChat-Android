package com.voxchat.messenger.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.voxchat.messenger.R

object AvatarGenerator {

    fun generateAvatarDrawable(context: Context, name: String, size: Int = 120): Drawable {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val colors = context.resources.getIntArray(R.array.avatar_colors)
        
        // Генерируем цвет на основе имени
        val colorIndex = Math.abs(name.hashCode()) % colors.size
        val backgroundColor = colors[colorIndex]

        // Получаем инициалы
        val initials = getInitials(name)

        return object : Drawable() {
            private val bgPaint = Paint(paint).apply {
                color = backgroundColor
                style = Paint.Style.FILL
            }
            
            private val textPaint = Paint(paint).apply {
                color = Color.WHITE
                textAlign = Paint.Align.CENTER
                textSize = size * 0.4f
                isFakeBoldText = true
                typeface = android.graphics.Typeface.DEFAULT
            }

            override fun draw(canvas: Canvas) {
                val bounds = bounds
                val centerX = bounds.centerX().toFloat()
                val centerY = bounds.centerY().toFloat()

                // Рисуем круглый фон
                canvas.drawCircle(centerX, centerY, bounds.width() / 2f, bgPaint)

                // Рисуем инициалы
                val textBounds = android.graphics.Rect()
                textPaint.getTextBounds(initials, 0, initials.length, textBounds)
                val textY = centerY - (textPaint.descent() + textPaint.ascent()) / 2
                canvas.drawText(initials, centerX, textY, textPaint)
            }

            override fun setAlpha(alpha: Int) {}
            override fun setColorFilter(colorFilter: android.graphics.ColorFilter?) {}
            override fun getOpacity(): Int = android.graphics.PixelFormat.OPAQUE
        }
    }

    private fun getInitials(name: String): String {
        val parts = name.trim().split("\\s+".toRegex())
        return when {
            parts.isEmpty() -> "?"
            parts.size == 1 -> parts[0].take(2).uppercase()
            else -> "${parts[0].first()}${parts[1].first()}".uppercase()
        }
    }
}
