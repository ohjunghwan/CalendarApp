package me.devhi.calendar.ui.adapter.diffcallback

import androidx.recyclerview.widget.DiffUtil
import me.devhi.calendar.data.vo.MonthAndDays

class MonthVODiffCallback : DiffUtil.ItemCallback<MonthAndDays>() {
    override fun areItemsTheSame(oldItem: MonthAndDays, newItem: MonthAndDays) =
        oldItem.monthVO.id == newItem.monthVO.id

    override fun areContentsTheSame(oldItem: MonthAndDays, newItem: MonthAndDays) =
        false
}
