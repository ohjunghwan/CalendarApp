package me.devhi.calendar.data.vo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*


@Entity(
    tableName = "day",
    foreignKeys = [ForeignKey(
        entity = MonthVO::class,
        parentColumns = ["id"],
        childColumns = ["monthId"]
    )],
    indices = [Index("monthId")]
)
data class DayVO(
    val type: Type,
    val monthId: Long,
    val day: GregorianCalendar? = null,
    var memo: String? = null,
    var emotion: Emotion? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

enum class Type {
    EMPTY, NORMAL;
}

enum class Emotion {
    CRYING,
    SHOCK,
    LAUGH,
    SMILE,
    SAD,
    THINK,
    SILENT,
    SO_SO
}