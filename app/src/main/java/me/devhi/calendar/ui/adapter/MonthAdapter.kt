package me.devhi.calendar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.devhi.calendar.R
import me.devhi.calendar.data.vo.MonthAndDays
import me.devhi.calendar.ui.adapter.diffcallback.MonthVODiffCallback
import me.devhi.calendar.databinding.ViewCalendarBinding
import me.devhi.calendar.viewmodel.MonthListViewModel

class MonthAdapter(val viewModel: MonthListViewModel) :
    ListAdapter<MonthAndDays, MonthAdapter.ViewHolder>(MonthVODiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthAdapter.ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_calendar,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MonthAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ViewCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MonthAndDays) {
            val adapter = DayAdapter(viewModel::onDayItemClick)
            binding.month = item.monthVO
            binding.recyclerView.adapter = adapter
            adapter.submitList(item.days)
        }
    }
}
