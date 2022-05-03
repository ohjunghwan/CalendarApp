package me.devhi.calendar.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import me.devhi.calendar.data.vo.DayVO

@Dao
interface DayDao {
    @Insert
    suspend fun insertAll(vararg days: DayVO)

    @Update
    suspend fun updateDay(day: DayVO)
}
