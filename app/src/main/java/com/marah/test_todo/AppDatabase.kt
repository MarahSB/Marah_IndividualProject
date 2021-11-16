package com.marah.test_todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

  abstract fun taskDao(): TaskDao
   // abstract val taskDao: TaskDao

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context : Context): AppDatabase? {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "app_database").build()
            }
            return INSTANCE
        }
    }
}