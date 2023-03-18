package com.example.prac_android.step6.sleeptracker

import android.app.Application
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.prac_android.step6.database.SleepDatabaseDao
import com.example.prac_android.step6.database.SleepNight
import com.example.prac_android.step6.formatNights
import kotlinx.coroutines.launch

class SleepTrackerViewModel (
    private val database: SleepDatabaseDao,
    application: Application) : AndroidViewModel(application) {
    private var tonight = MutableLiveData<SleepNight?>()
    val nights = database.getAllNights()
    private val _navigateToSleepQuality = MutableLiveData<SleepNight?>()
    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
    get() = _showSnackbarEvent

    val navigateToSleepQuality: MutableLiveData<SleepNight?>
    get() = _navigateToSleepQuality

    private val _navigateToSleepDetail = MutableLiveData<Long?>()
    val navigateToSleepDetail
    get() = _navigateToSleepDetail

    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    val startButtonVisible = Transformations.map(tonight){
        it == null
    }

    val stopButtonVisible = Transformations.map(tonight){
        it != null
    }

    val clearButtonVisible = Transformations.map(nights){
        it?.isNotEmpty()
    }

    init {
        initializeTonight()
    }
    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }
    private suspend fun getTonightFromDatabase(): SleepNight? {
        var night = database.getTonight()
        if(night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }
        return night
    }
    fun onStartTracking() {
        viewModelScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }
    private suspend fun insert(night: SleepNight) {
        database.insert(night)
    }
    fun onStopTracking() {
        viewModelScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
            _navigateToSleepQuality.value = oldNight
        }
    }
    private suspend fun update(night: SleepNight) {
        database.update(night)
    }
    fun onClear() {
        viewModelScope.launch {
            clear()
            tonight.value = null
            _showSnackbarEvent.value = true
        }
    }
    private suspend fun clear() {
        database.clear()
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onSleepNightClicked(id: Long) {
        _navigateToSleepDetail.value = id
    }

    fun onSleepDetailNavigated() {
        _navigateToSleepDetail.value = null
    }
}