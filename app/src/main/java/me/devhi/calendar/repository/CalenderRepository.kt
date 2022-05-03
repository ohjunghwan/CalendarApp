package me.devhi.calendar.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import me.devhi.calendar.data.dao.DayDao
import me.devhi.calendar.data.dao.MonthDao
import me.devhi.calendar.data.vo.DayVO
import me.devhi.calendar.data.vo.MonthAndDays

interface CalenderRepository {
    fun getMonthList(): Flow<List<MonthAndDays>>

    suspend fun updateDay(day: DayVO)
}


class CalenderRepositoryImpl(
    private val monthDao: MonthDao,
    private val dayDao: DayDao
) : CalenderRepository {
    override fun getMonthList() = monthDao.getAll()

    override suspend fun updateDay(day: DayVO) {
        dayDao.updateDay(day)
    }

}