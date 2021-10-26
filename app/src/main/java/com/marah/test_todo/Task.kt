package com.marah.test_todo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
//import kotlinx.androidx.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tasks_table")
data class Task(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") var taskTitle: String,
    @ColumnInfo(name = "description")var taskDescription: String,
    @ColumnInfo(name = "current_date") var creationDate: String = System.currentTimeMillis().toString(),
    @ColumnInfo(name = "due_date") var dueDate: Long
): Parcelable
