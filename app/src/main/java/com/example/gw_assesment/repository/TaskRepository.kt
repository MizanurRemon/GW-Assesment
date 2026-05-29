package com.example.gw_assesment.repository

import android.util.Log
import com.example.gw_assesment.datastore.PreferenceManager
import com.example.gw_assesment.di.OdooDatabase
import com.example.gw_assesment.network.OdooApiService
import com.example.gw_assesment.network.models.OdooRequest
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val apiService: OdooApiService,
    private val preferenceManager: PreferenceManager,
    @OdooDatabase private val db: String
) {
    suspend fun createTask(
        name: String,
        description: String,
        dueDate: String
    ): Result<Int> {
        val uid = preferenceManager.userId.first() ?: return Result.failure(Exception("User not logged in"))
        val passWord = preferenceManager.userToken.first()

        val values = mapOf(
            "name" to name,
            "date_deadline" to dueDate,
            "description" to description
        )

        val params = mapOf(
            "service" to "object",
            "method" to "execute_kw",
            "args" to listOf(
                db,
                uid,
                passWord,
                "project.task",
                "create",
                listOf(values),
                mapOf<String, Any>()
            )
        )

        val request = OdooRequest(
            params = params
        )

        return try {
            val response = apiService.call(request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.error != null) {
                    Log.d("dataxx", "body error: ${body.error}")
                    Result.failure(Exception(body.error.message))
                } else {
                    val result = body?.result
                    when (result) {
                        is Double -> Result.success(result.toInt())
                        is Int -> Result.success(result)
                        else -> Result.failure(Exception("Failed to create task: Invalid response"))
                    }
                }
            } else {
                Log.d("dataxx", "createTask: ${response}")
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.d("dataxx", "createTask exception: ${e}")
            Result.failure(e)
        }
    }
}
