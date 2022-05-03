package me.devhi.calendar.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import me.devhi.calendar.data.vo.MonthAndDays
import me.devhi.calendar.data.vo.MonthVO

@Dao
interface MonthDao {

    @Transaction
    @Query("SELECT * FROM month")
    fun getAll(): Flow<List<MonthAndDays>>

    @Insert
    suspend fun insert(monthVO: MonthVO): Long
}