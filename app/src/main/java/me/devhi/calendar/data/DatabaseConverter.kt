package me.devhi.calendar.data

import androidx.room.TypeConverter
import me.devhi.calendar.data.vo.Type
import java.text.SimpleDateFormat
import java.util.*


class DatabaseConverter {
    companion object {
        val format = SimpleDateFormat("dd MM yyyy")
    }

    @TypeConverter
    fun stringToCalendar(value: String?): GregorianCalendar? {
        if (value == null) return null
        val date: Date = format.parse(value)
        val cal = GregorianCalendar()
        cal.time = date
        return cal
    }

    @TypeConverter
    fun calendarToString(calendar: GregorianCalendar?): String? {
        if (calendar == null) return null
        return format.format(calendar.time)
    }

    @TypeConverter
    fun typeToString(type: Type): String {
        return type.name
    }

    @TypeConverter
    fun stringToType(str: String): Type {
        return Type.valueOf(str)
    }

}