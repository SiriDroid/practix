package com.timidgiraffe.practix.domain

interface PracticeRepository {
    suspend fun getAllSessions(): List<Practice>
    suspend fun removeSession(id: Int)
    suspend fun removeAllSessions()
    suspend fun getSessionIdByName(name: String): Long
    suspend fun updateSessionCount(name: String, count: Long)
    suspend fun insertSession(name: String, count: Long, dateAdded: String)
    suspend fun sessionExists(name: String): Boolean
}


data class Practice(
    val id: Long,
    val name: String,
    val count: Long,
    val dateAdded: String
)