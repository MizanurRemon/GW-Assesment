package com.example.gw_assesment.utils

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDate(): String {
    return SimpleDateFormat(
        "yyyy-MM-dd",
        Locale.ENGLISH
    ).format(Date())
}

enum class StatusType(
    val status: String,
    val statusColor: Color,
    val statusBackgroundColor: Color,
    val state: String
) {
    COMPLETED(
        status = "Completed",
        statusColor = Color(0xFF1E88E5),
        statusBackgroundColor = Color(0xFF1E88E5).copy(alpha = .2f),
        state = "1_done"
    ),

    IN_PROGRESS(
        status = "In Progress",
        statusColor = Color(0xFFE65100),
        statusBackgroundColor = Color(0xFFE65100).copy(alpha = .2f),
        state = "01_in_progress"
    );


    companion object {
        fun fromType(value: String?): StatusType {
            return entries.firstOrNull {
                it.state.equals(value, ignoreCase = true)
            } ?: IN_PROGRESS
        }
    }
}