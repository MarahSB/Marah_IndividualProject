package com.marah.test_todo

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AppRepository(context: Context) {

    private val appDB = AppDatabase.getAppDataBase(context)!!

    suspend fun getAllTasks(): List<Task> = withContext(Dispatchers.IO) {
        appDB.taskDao().getAlphabetizedTasks() }

    suspend fun insertTask(task: Task) = withContext(Dispatchers.IO) {
        appDB.taskDao().insert(task) }


    suspend fun updateTask(task: Task)  = withContext(Dispatchers.IO) {
        appDB.taskDao().update(task) }

    suspend fun deleteTask(task: Task) = withContext(Dispatchers.IO) {
        appDB.taskDao().delete(task) }

    suspend fun doneTask() = withContext(Dispatchers.IO) {
        appDB.taskDao().getDoneTasks() }


}