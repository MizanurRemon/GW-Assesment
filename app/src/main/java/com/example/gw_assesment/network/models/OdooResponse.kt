package com.example.gw_assesment.network.models

import com.google.gson.annotations.SerializedName

data class OdooResponse<T>(
    @SerializedName("jsonrpc")
    val jsonrpc: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("result")
    val result: T? = null,
    @SerializedName("error")
    val error: OdooError? = null
)

data class OdooError(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: Any? = null
)
