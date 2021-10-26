package com.marah.test_todo

import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * From tasks_table")
    fun getAllTasks(): List<Task>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("select * from tasks_table order by task ASC ")
    suspend fun getAlphabetizedTasks():List<Task>
}
