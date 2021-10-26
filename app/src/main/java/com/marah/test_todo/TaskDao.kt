package com.marah.test_todo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Query("select * from tasks_table order by title ASC ")
    fun getAlphabetizedTasks():List<Task>

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

//    @Query("SELECT * From tasks_table")
//    fun getAllTasks(): LiveData<List<Task>>



}