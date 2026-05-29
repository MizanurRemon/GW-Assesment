package com.example.gw_assesment.network.models

import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class OdooRequest<out T>(
    @SerializedName("jsonrpc")
    val jsonrpc: String = "2.0",
    @SerializedName("method")
    val method: String = "call",
    @SerializedName("params")
    val params: T,
    @SerializedName("id")
    val id: Int? = Random.nextInt(100, 10000)
)

data class OdooParams(
    @SerializedName("service")
    val service: String,
    @SerializedName("method")
    val method: String,
    @SerializedName("args")
    val args: List<Any>
)
