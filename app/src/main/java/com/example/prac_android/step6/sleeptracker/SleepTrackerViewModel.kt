package com.example.prac_android.step6.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.prac_android.step6.database.SleepDatabaseDao.SleepDatabaseDao

class SleepTrackerViewModel (
    val database: SleepDatabaseDao,
    application: Application) : AndroidViewModel(application) {
}