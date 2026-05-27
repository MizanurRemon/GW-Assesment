package com.example.gw_assesment.utils

fun String.capitalizeFirstChar(): String {
    return this.replaceFirstChar { it.uppercase() }
}