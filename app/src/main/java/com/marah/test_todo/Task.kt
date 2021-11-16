package com.marah.test_todo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter
//import kotlinx.androidx.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tasks_table")
data class Task(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "title") var taskTitle: String,
    @ColumnInfo(name = "description") var taskDescription: String,
    @ColumnInfo(name = "due_date") var dueDate: String,
    @ColumnInfo(name = "current_date") var creationDate: String,
    @ColumnInfo(name = "completed") var completed: Boolean = false,
    @ColumnInfo(name = "today") var today: String = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString(),



    ): Parcelable

