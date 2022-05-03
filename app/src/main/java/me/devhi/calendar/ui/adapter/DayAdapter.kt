package me.devhi.calendar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.devhi.calendar.R
import me.devhi.calendar.uistate.toUiState
import me.devhi.calendar.data.vo.DayVO
import me.devhi.calendar.ui.adapter.diffcallback.DayVODiffCallback
import me.devhi.calendar.data.vo.Type
import me.devhi.calendar.ui.viewholder.NormalViewHolder
import me.devhi.calendar.ui.viewholder.EmptyViewHolder
import java.lang.Exception

private const val NORMAL_TYPE = 0
private const val EMPTY_TYPE = 1

class DayAdapter(
    private val onItemClick: (DayVO) -> Unit
) :
    ListAdapter<DayVO, ViewHolder>(DayVODiffCallback()) {
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            Type.NORMAL -> NORMAL_TYPE
            Type.EMPTY -> EMPTY_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            NORMAL_TYPE -> NormalViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_day,
                    parent,
                    false
                )
            )
            EMPTY_TYPE -> EmptyViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_day_empty,
                    parent,
                    false
                )
            )
            else -> throw Exception("Invalid view holder in DayAdapter")
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is NormalViewHolder) {
            holder.bind(getItem(position).toUiState(onItemClick))
        }
    }

}