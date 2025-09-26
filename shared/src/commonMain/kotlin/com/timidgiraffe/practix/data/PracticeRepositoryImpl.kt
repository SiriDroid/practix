package com.timidgiraffe.practix.data

import com.timidgiraffe.practix.AppDatabase
import com.timidgiraffe.practix.domain.Practice
import com.timidgiraffe.practix.domain.PracticeRepository

class PracticeRepositoryImpl(private val database: AppDatabase) : PracticeRepository {
    override suspend fun getAllSessions(): List<Practice> {
        return database.appDatabaseQueries.getAllSessions().executeAsList().map {
            Practice(
                id = it.id,
                name = it.name,
                count = it.count,
                dateAdded = it.dateAdded)
        }
    }

    override suspend fun removeSession(id: Int) {
        database.appDatabaseQueries.removeSession(id.toLong())
    }

    override suspend fun removeAllSessions() {
        database.appDatabaseQueries.removeAllSessions()
    }

    override suspend fun getSessionIdByName(name: String): Long {
        return database.appDatabaseQueries.getSessionById(name).executeAsOne()
    }

    override suspend fun updateSessionCount(name: String, count: Long) {
        database.appDatabaseQueries.updateSessionCount(count, name)
    }

    override suspend fun insertSession(name: String, count: Long, dateAdded: String) {
        database.appDatabaseQueries.insertSession(name, count, dateAdded)
    }

    override suspend fun sessionExists(name: String): Boolean {
        return database.appDatabaseQueries.sessionExists(name).executeAsOne()
    }
}