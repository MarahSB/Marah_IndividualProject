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

     fun update(task: Task) =  viewModelScope.launch{
        repo.updateTask(task)
        }

     fun delete(task: Task) = viewModelScope.launch {
        repo.deleteTask(task)
    }

    fun insert(task: Task) = viewModelScope.launch{
            repo.insertTask(task)
        }
//
//    fun getDoneTasks(): MutableLiveData<List<Task>> {
//        val todayTasks = MutableLiveData<List<Task>>()
//        viewModelScope.launch {
//            todayTasks.postValue(repo.doneTask())
//        }
//        return  todayTasks
//    }
}