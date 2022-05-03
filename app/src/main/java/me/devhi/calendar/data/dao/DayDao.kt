package me.devhi.calendar.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import me.devhi.calendar.data.vo.DayVO

@Dao
interface DayDao {
    @Insert
    suspend fun insertDay(day: DayVO)

    @Update
    suspend fun updateDay(day: DayVO)

    @Query("SELECT * FROM day")
    fun selectAll(): LiveData<List<DayVO>>
}
