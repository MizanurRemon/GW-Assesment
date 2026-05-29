package com.example.gw_assesment.repository

import android.util.Log
import com.example.gw_assesment.datastore.PreferenceManager
import com.example.gw_assesment.di.OdooDatabase
import com.example.gw_assesment.model.TaskResponse
import com.example.gw_assesment.network.OdooApiService
import com.example.gw_assesment.network.models.OdooRequest
import com.example.gw_assesment.utils.StatusType
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val apiService: OdooApiService,
    private val preferenceManager: PreferenceManager,
    @param:OdooDatabase private val db: String
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
                    when (val result = body?.result) {
                        is Double -> Result.success(result.toInt())
                        is Int -> Result.success(result)
                        else -> Result.failure(Exception("Failed to create task: Invalid response"))
                    }
                }
            } else {
                Log.d("dataxx", "createTask: $response")
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.d("dataxx", "createTask exception: $e")
            Result.failure(e)
        }
    }

    suspend fun getTasks(): Result<List<TaskResponse>> {
        val uid = preferenceManager.userId.first() ?: return Result.failure(Exception("User not logged in"))
        val passWord = preferenceManager.userToken.first()

        val params = mapOf(
            "service" to "object",
            "method" to "execute_kw",
            "args" to listOf(
                db,
                uid,
                passWord,
                "project.task",
                "search_read",
                listOf<Any>(),
                mapOf(
                    "fields" to listOf("id", "name", "description","state", "date_deadline")
                )
            )
        )

        val request = OdooRequest(params = params)

        return try {
            val response = apiService.call(request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.error != null) {
                    Result.failure(Exception(body.error.message))
                } else {
                    val result = body?.result as? List<*>
                    val tasks: List<TaskResponse> = result?.mapNotNull {
                        val map = it as? Map<*, *>
                        if (map != null) {
                            TaskResponse(
                                id = (map["id"] as? Double)?.toInt() ?: 0,
                                title = map["name"] as? String ?: "",
                                description = map["description"] as? String ?: "",
                                dueDate = map["date_deadline"] as? String ?: "",
                                stage = map["state"] as? String ?: "",
                            )
                        } else null
                    } ?: emptyList()
                    Result.success(tasks)
                }
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateTask(
        taskId: Int,
        status: String
    ): Result<Int> {
        val uid = preferenceManager.userId.first() ?: return Result.failure(Exception("User not logged in"))
        val passWord = preferenceManager.userToken.first()
        val state = StatusType.fromStatus(status).state

        val params = mapOf(
            "service" to "object",
            "method" to "execute_kw",
            "args" to listOf(
                db,
                uid,
                passWord,
                "project.task",
                "write",
                listOf(
                    listOf(taskId),
                    mapOf("state" to state)
                )
            )
        )

        val request = OdooRequest(params = params)

        return try {
            val response = apiService.call(request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.error != null) {
                    Result.failure(Exception(body.error.message))
                } else {
                    Result.success(taskId)
                }
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
