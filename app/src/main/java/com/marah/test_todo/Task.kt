package com.marah.test_todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks_table")
data class Task(@PrimaryKey val id: Int,
                @ColumnInfo(name = "task") var taskTitle: String,
                var taskDescription: String,
                var taskDate: Date)