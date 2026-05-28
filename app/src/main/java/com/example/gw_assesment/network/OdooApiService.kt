package com.example.gw_assesment.network

import com.example.gw_assesment.network.models.OdooRequest
import com.example.gw_assesment.network.models.OdooResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OdooApiService {
    @POST("/jsonrpc")
    suspend fun call(
        @Body request: OdooRequest<@JvmSuppressWildcards Any>,
    ): Response<OdooResponse<@JvmSuppressWildcards Any>>
}
