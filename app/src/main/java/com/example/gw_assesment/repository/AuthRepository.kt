package com.example.gw_assesment.repository

import android.util.Log
import com.example.gw_assesment.datastore.PreferenceManager
import com.example.gw_assesment.di.OdooDatabase
import com.example.gw_assesment.network.OdooApiService
import com.example.gw_assesment.network.models.OdooRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val apiService: OdooApiService,
    private val preferenceManager: PreferenceManager,
    @OdooDatabase private val db: String,
) {
    suspend fun login(user: String, pass: String): Result<Int> {
        val loginParams = mapOf(
            "service" to "common",
            "method" to "authenticate",
            "args" to listOf(db, user, pass, emptyMap<String, Any>())
        )
        
        val request = OdooRequest(
            params = loginParams
        )

        return try {
            val response = apiService.call(request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.error != null) {
                    return Result.failure(Exception(body.error.message))
                }
                
                val result = body?.id
                when (result) {
                    is Int -> {
                        preferenceManager.saveUserId(result)
                        preferenceManager.saveLoginStatus(true)
                        Result.success(result)
                    }
                    else -> Result.failure(Exception("Login failed: Invalid response format"))
                }
            } else {
                Log.d("dataxx", "login: ${response.message()}")
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.d("dataxx", "exc: ${e.message}")
            Result.failure(e)
        }
    }
}
