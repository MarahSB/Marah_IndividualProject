package com.marah.test_todo

import androidx.room.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

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

    @Query("select * from tasks_table where due_date == `today`")
    fun getDoneTasks():List<Task>

}
//
//fun curDate():String{
//    val current1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
//    val formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd")
//    val nowDate = current1.format(formatter1)
//    return nowDate
//}
