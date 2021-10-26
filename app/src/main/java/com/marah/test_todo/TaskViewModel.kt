package com.marah.test_todo


import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TaskViewModel (context: Application): AndroidViewModel(context){

    private val repo = AppRepository(context)

    fun getAllTasks(): MutableLiveData<List<Task>> {
        val tasks = MutableLiveData<List<Task>>()
        viewModelScope.launch {
            tasks.postValue(repo.getAllTasks())
        }
        return  tasks
    }

    suspend fun update(task: Task): Unit = repo.updateTask(task)

    suspend fun delete(task: Task) = viewModelScope.launch {
        repo.deleteTask(task)
    }

    fun insert(task: Task) = viewModelScope.launch{
            repo.insertTask(task)
        }
}