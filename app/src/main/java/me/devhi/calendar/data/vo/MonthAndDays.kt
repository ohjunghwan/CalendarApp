package me.devhi.calendar.data.vo

import androidx.room.Embedded
import androidx.room.Relation

class MonthAndDays {
    @Embedded
    lateinit var monthVO: MonthVO

    @Relation(
        parentColumn = "id",
        entityColumn = "monthId"
    )

    var days: List<DayVO> = ArrayList()

    override fun toString(): String {
        return days.size.toString()
    }
}