package com.example.gw_assesment.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDate(): String {
    return SimpleDateFormat(
        "MMM dd, yyyy",
        Locale.ENGLISH
    ).format(Date())
}