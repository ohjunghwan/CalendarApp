package me.devhi.calendar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.devhi.calendar.data.vo.DayVO
import me.devhi.calendar.repository.CalenderRepository
import me.devhi.calendar.utils.mvvm.SingleLiveEvent


class MonthListViewModel(
    private val calenderRepository: CalenderRepository
) : ViewModel() {
    var currentPosition = 0
    var title = MutableLiveData<Long>()
    var monthList = calenderRepository.getMonthList().asLiveData()

    val goToPosition = SingleLiveEvent<Int>()
    val showMemoDialog = SingleLiveEvent<DayVO>()

    fun goPrevMonth() {
        updatePosition(currentPosition - 1)
        goToPosition.value = currentPosition
    }

    fun goNextMonth() {
        updatePosition(currentPosition + 1)
        goToPosition.value = currentPosition
    }

    fun updatePosition(position: Int) {
        val list = monthList.value ?: return
        if (position > 0 && position < list.size) {
            currentPosition = position
            title.value = list[position].monthVO.time
        }
    }

    fun onDayItemClick(day: DayVO) {
        showMemoDialog.value = day
    }

    fun addMemo(day: DayVO) {
        viewModelScope.launch {
            calenderRepository.updateDay(day)
        }
    }
}
