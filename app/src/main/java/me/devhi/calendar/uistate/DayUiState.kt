package me.devhi.calendar.uistate

data class DayUiState(
    val dayId: Long,
    val day: String,
    val emotionDrawableId: Int,
    val memo: String?,
    val onDayItemClick: () -> Unit
)

