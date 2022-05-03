package me.devhi.calendar.ui.viewholder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.devhi.calendar.uistate.DayUiState
import me.devhi.calendar.databinding.ItemDayBinding

class NormalViewHolder(
    private val binding: ItemDayBinding
) : ViewHolder(binding.root) {

    fun bind(item: DayUiState) {
        binding.item = item
        val textColor = when {
            adapterPosition % 7 == 0 -> Color.RED
            adapterPosition % 7 == 6 -> Color.BLUE
            else -> Color.BLACK
        }
        binding.dayText.setTextColor(textColor)
    }
}