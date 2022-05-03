package me.devhi.calendar.uistate

import me.devhi.calendar.R
import me.devhi.calendar.data.vo.DayVO
import me.devhi.calendar.data.vo.Emotion
import me.devhi.calendar.utils.toDayString


fun DayVO.toUiState(
    onItemClick: (DayVO) -> Unit
) = DayUiState(
    dayId = this.monthId,
    day = this.day?.toDayString() ?: "",
    emotionDrawableId = this.emotion.toDrawableId(),
    memo = this.memo,
    onDayItemClick = { onItemClick(this) },
)

fun Emotion?.toDrawableId() = when (this) {
    Emotion.CRYING -> R.drawable.emoji_crying
    Emotion.SHOCK -> R.drawable.emoji_shock
    Emotion.LAUGH -> R.drawable.emoji_laugh
    Emotion.SMILE -> R.drawable.emoji_smile
    Emotion.SAD -> R.drawable.emoji_sad
    Emotion.THINK -> R.drawable.emoji_think
    Emotion.SILENT -> R.drawable.emoji_silent
    Emotion.SO_SO -> R.drawable.emoji_soso
    else -> 0
}