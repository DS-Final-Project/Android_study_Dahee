package com.example.prac_android.step6.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prac_android.step6.database.SleepDatabaseDao
import kotlinx.coroutines.launch

class SleepQualityViewModel (
    private val SleepNightKey: Long = 0L,
            val database: SleepDatabaseDao) : ViewModel() {

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    val navigateToSleepTracker: LiveData<Boolean?>
    get() = _navigateToSleepTracker

    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    fun onSetSleepQuality(quality: Int) {
        viewModelScope.launch {
            val tonight = database.get(SleepNightKey) ?: return@launch
            tonight.sleepQuality = quality
            database.update(tonight)

            _navigateToSleepTracker.value = true
        }
    }
}