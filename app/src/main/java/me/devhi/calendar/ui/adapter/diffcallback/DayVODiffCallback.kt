package me.devhi.calendar.ui.adapter.diffcallback

import androidx.recyclerview.widget.DiffUtil
import me.devhi.calendar.data.vo.DayVO

class DayVODiffCallback : DiffUtil.ItemCallback<DayVO>() {

    override fun areItemsTheSame(oldItem: DayVO, newItem: DayVO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DayVO, newItem: DayVO): Boolean {
        return oldItem.memo == newItem.memo
    }
}
