package com.example.prac_android.step6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prac_android.step6.database.SleepDatabaseDao.SleepDatabaseDao

@Database(entities = [SleepNight::class], version = 1,  exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {
    abstract val sleepDatabaseDao: SleepDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}