package me.devhi.calendar.data.vo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "month")
data class MonthVO(val time: Long) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}