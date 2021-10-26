package com.marah.test_todo

import androidx.annotation.WorkerThread

class AppRepository(private val taskDao: TaskDao) {

    suspend fun allTasksList(){
    val allTasks: List<Task> = taskDao.getAlphabetizedTasks()
}

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }
}