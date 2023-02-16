package com.example.prac_android.step6.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight (
    //nightId를 기본 키로 식별하려면 @PrimarKey를 사용
    //room이 각 entity에 대한 ID를 생성하도록
    //매개변수 autoGenerate을 true로 설정
    @PrimaryKey(autoGenerate = true)
    var nightId: Long = 0L,
    //나머지 속성엔 @ColumnInfo 사용
    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,
    @ColumnInfo(name = "quality_rating")
    var sleepQuality: Int = -1
)